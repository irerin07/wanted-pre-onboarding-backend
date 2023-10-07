package com.tistory.irerin07.wantedpreonboardingbackend.common.configuration.jpa.querydsl.support;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.querydsl.QSort;
import org.springframework.lang.NonNull;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberOperation;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @author 민경수
 * @description query dsl repository pagination support
 * @since 2023.10.06
 **********************************************************************************************************************/
public class QueryDslRepositoryPaginationSupport extends QuerydslRepositorySupport {

  private final JPAQueryFactory queryFactory;


  public QueryDslRepositoryPaginationSupport(Class<?> domainClass, JPAQueryFactory queryFactory) {
    super(domainClass);
    this.queryFactory = queryFactory;
  }


  protected JPAQueryFactory getQueryFactory() {
    return this.queryFactory;
  }

  protected int saveAllNativeQuery(@NonNull String tableName, @NonNull List<String> columnNames, @NonNull List<List<Object>> values) {
    //@formatter:off
    String marker = columnNames.stream().map(v -> "?").collect(Collectors.joining(", "));

    Query nativeQuery = getEntityManager().createNativeQuery("INSERT INTO "
      + tableName + " (" + String.join(", ", columnNames) + ") VALUES "
      + values.stream().map(v -> "(" + marker + ")").collect(Collectors.joining(", ")));

    List<Object> flatValues = values.stream().flatMap(Collection::stream).collect(Collectors.toList());
    for (int i = 0; i < flatValues.size(); i++) {
      nativeQuery.setParameter(i + 1, flatValues.get(i));
    }
    //@formatter:on

    return nativeQuery.executeUpdate();
  }

  protected <T> JPAQuery<T> select(Expression<T> expr) {
    return getQueryFactory().select(expr);
  }

  protected JPAQuery<Integer> selectOne() {
    return getQueryFactory().selectOne();
  }

  protected <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
    return getQueryFactory().selectFrom(from);
  }

  protected <T> Page<T> applyPagination(@NonNull Pageable pageable, @NonNull JPQLQuery<T> query) {
    Querydsl querydsl = getValidatedQueryDsl();

    List<T> queryResults = querydsl.applyPagination(pageable, query).fetch();
    long totalCount = query.fetchCount();

    // Total Contents 개수를 초과 하는 pageNo이 넘어 올 경우, 마지막 데이터 재 조회 처리
    if (pageable.getOffset() >= totalCount && totalCount > 0) {
      int lastPageNo = (int) totalCount / pageable.getPageSize();
      pageable = PageRequest.of(lastPageNo, pageable.getPageSize(), pageable.getSort());
      queryResults = querydsl.applyPagination(pageable, query).fetch();
    }

    return new PageImpl<>(queryResults, pageable, totalCount);
  }

  /**
   * @author takjun.lee
   * @since issue core/shareit-boot-starters-parent #32 페이징 성능 개선
   */
  protected <T> Slice<T> applySlicePagination(@NonNull Pageable pageable, @NonNull JPQLQuery<T> query) {
    Querydsl queryDsl = getValidatedQueryDsl();

    List<T> queryResults = queryDsl.applyPagination(pageable, query).fetch();

    return new SliceImpl<>(queryResults, pageable, queryResults.size() >= pageable.getPageSize());
  }

  /**
   * @author takjun.lee
   * @since issue core/shareit-boot-starters-parent #32 페이징 성능 개선
   */
  protected <T> Page<T> applyParallelPagination(@NonNull final Pageable pageable, @NonNull JPQLQuery<T> query) {
    Querydsl querydsl = getValidatedQueryDsl();

    JPQLQuery<T> paginationQuery = querydsl.applyPagination(pageable, query);

    try {
      CompletableFuture<List<T>> queryResults = CompletableFuture.supplyAsync(paginationQuery::fetch);
      CompletableFuture<Long> totalCount = CompletableFuture.supplyAsync(query::fetchCount);

      return new PageImpl<>(queryResults.get(), pageable, totalCount.get());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    } catch (CancellationException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @author takjun.lee
   * @since issue core/shareit-boot-starters-parent #32 페이징 성능 개선
   */
  private Querydsl getValidatedQueryDsl() {
    Querydsl querydsl = getQuerydsl();

    if (null == querydsl) {
      throw new IllegalStateException("QueryDsl is null");
    }

    return querydsl;
  }

  protected <T> JPQLQuery<T> applySorting(@NonNull Sort sort, @NonNull JPQLQuery<T> query) {
    if (sort.isUnsorted()) {
      return query;
    }

    if (sort instanceof QSort) {
      return addOrderByFrom((QSort) sort, query);
    }

    return addOrderByFrom(sort, query);
  }

  @SuppressWarnings("PMD")
  private <T> JPQLQuery<T> addOrderByFrom(QSort qsort, JPQLQuery<T> query) {
    List<OrderSpecifier<?>> orderSpecifiers = qsort.getOrderSpecifiers();
    return query.orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]));
  }

  private <T> JPQLQuery<T> addOrderByFrom(@NonNull Sort sort, @NonNull JPQLQuery<T> query) {
    for (Sort.Order order : sort) {
      query.orderBy(toOrderSpecifier(order));
    }

    return query;
  }

  @SuppressWarnings({"rawtypes", "unchecked", "java:S3740"})
  private OrderSpecifier toOrderSpecifier(@NonNull Sort.Order order) {
    return new OrderSpecifier(order.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC, buildOrderPropertyPathFrom(order),
      toQueryDslNullHandling(order.getNullHandling()));
  }

  private OrderSpecifier.NullHandling toQueryDslNullHandling(@NonNull Sort.NullHandling nullHandling) {
    switch (nullHandling) {
      case NULLS_FIRST:
        return OrderSpecifier.NullHandling.NullsFirst;
      case NULLS_LAST:
        return OrderSpecifier.NullHandling.NullsLast;
      case NATIVE:
      default:
        return OrderSpecifier.NullHandling.Default;
    }
  }

  private Expression<?> buildOrderPropertyPathFrom(@NonNull Sort.Order order) {
    PropertyPath path = PropertyPath.from(order.getProperty(), getBuilder().getType());
    Expression<?> sortPropertyExpression = getBuilder();

    while (path != null) {
      if (!path.hasNext() && order.isIgnoreCase()) {
        sortPropertyExpression = Expressions.stringPath((Path<?>) sortPropertyExpression, path.getSegment()).lower();
      } else {
        sortPropertyExpression = Expressions.path(path.getType(), (Path<?>) sortPropertyExpression, path.getSegment());
      }

      path = path.next();
    }

    return sortPropertyExpression;
  }

  protected BooleanExpression getBooleanExpression(Long value, NumberPath<Long> numberPath) {
    return Optional.ofNullable(value).map(numberPath::eq).orElse(null);
  }

  protected BooleanExpression getBooleanExpression(Integer value, NumberPath<Integer> numberPath) {
    return Optional.ofNullable(value).map(numberPath::eq).orElse(null);
  }

  protected BooleanExpression getBooleanExpression(Boolean value, BooleanPath booleanPath) {
    return Optional.ofNullable(value).map(booleanPath::eq).orElse(null);
  }

  protected BooleanExpression getBooleanExpression(String value, StringPath stringPath) {
    return Optional.ofNullable(value).filter(StringUtils::isNotBlank).map(String::trim).map(String::toLowerCase).map(stringPath::containsIgnoreCase).orElse(null);
  }

  protected BooleanExpression getBooleanExpression(String value, NumberPath<Long> numberPath) {
    if (StringUtils.isBlank(value)) {
      return null;
    }

    if (StringUtils.isNumeric(value)) {
      return Optional.of(value).map(String::trim).map(Long::parseLong).map(numberPath::eq).orElse(null);
    }

    return numberPath.eq(-1L);
  }

  protected BooleanExpression getBooleanExpression(LocalDate startDate, LocalDate endDate, DateTimePath<LocalDateTime> dateTimePath) {
    if (null == startDate || null == endDate) {
      return null;
    }

    return dateTimePath.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59, 9999));
  }

  protected <T extends Number & Comparable<?>> NumberOperation<T> avg(Class<? extends T> type, Expression<?>... args) {
    return Expressions.numberOperation(type, Ops.AggOps.AVG_AGG, args);
  }

}
