package com.tistory.irerin07.wantedpreonboardingbackend.autoconfigure.web.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

/**
 * @author 민경수
 * @description slice wrapper
 * @since 2023.10.07
 **********************************************************************************************************************/
public class SliceWrapper<T> {

  protected final Slice<?> page;
  protected final List<T> content;
  protected final int currentNumber;


  public SliceWrapper(Slice<T> page) {
    this(page, page.getContent());
  }

  public SliceWrapper(Slice<?> page, List<T> content) {
    this.page = page;
    this.content = content;
    this.currentNumber = page.getNumber() + 1;
  }


  public int getCurrentNumber() {
    return currentNumber;
  }

  public int getNumber() {
    return page.getNumber();
  }

  public int getSize() {
    return page.getSize();
  }

  public int getNumberOfElements() {
    return page.getNumberOfElements();
  }

  @JsonIgnore
  public List<T> getContent() {
    return this.content;
  }

  public boolean isHasContent() {
    return page.hasContent();
  }

  public PageSort getSort() {
    return new PageSort(page.getSort());
  }

  public boolean isFirst() {
    return page.isFirst();
  }

  public boolean isLast() {
    return page.isLast();
  }

  public boolean isHasNext() {
    return page.hasNext();
  }

  public boolean isHasPrevious() {
    return page.hasPrevious();
  }

  public Pageable getNextPageable() {
    return page.nextPageable();
  }

  public Pageable getPreviousPageable() {
    return page.previousPageable();
  }

  public int getNextPageNumber() {
    if (isHasNext()) {
      return getNextPageable().getPageNumber() + 1;
    }

    return 0;
  }

  public int getPreviousPageNumber() {
    if (isHasPrevious()) {
      return getPreviousPageable().getPageNumber() + 1;
    }

    return 0;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }


  @Getter
  public static class PageSort {

    private final Boolean sorted;
    private final Boolean unsorted;
    private final Boolean empty;

    private final List<Order> orders;


    public PageSort(Boolean sorted, Boolean unsorted, Boolean empty, List<Order> orders) {
      this.sorted = sorted;
      this.unsorted = unsorted;
      this.empty = empty;
      this.orders = orders;
    }

    public PageSort(Sort sort) {
      this(sort.isSorted(), sort.isUnsorted(), sort.isEmpty(), sort.get().map(Order::new).collect(Collectors.toList()));
    }


    @Getter
    public static class Order {

      private final String property;
      private final Sort.Direction direction;

      public Order(String property, Sort.Direction direction) {
        this.property = property;
        this.direction = direction;
      }

      public Order(Sort.Order order) {
        this(order.getProperty(), order.getDirection());
      }

    }

  }

}
