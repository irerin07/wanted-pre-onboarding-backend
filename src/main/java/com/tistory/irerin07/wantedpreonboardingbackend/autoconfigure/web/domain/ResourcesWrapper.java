package com.tistory.irerin07.wantedpreonboardingbackend.autoconfigure.web.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 민경수
 * @description resources wrapper
 * @since 2023.10.07
 **********************************************************************************************************************/
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourcesWrapper {

  private Map<String, Object> content;
  private Object search;
  private Object page;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }


  public static class Builder {

    private static final String RESOURCES_KEY = "resources";
    private static final String RESOURCE_KEY = "resource";
    private final ResourcesWrapper resourcesWrapper = new ResourcesWrapper();


    public Builder(Object object) {
      if (object instanceof PageWrapper) {
        PageWrapper<?> pageWrapper = (PageWrapper<?>) object;

        create(pageWrapper.getContent());
        resourcesWrapper.page = pageWrapper;

        return;
      }

      create(object);
    }

    public Builder(Page<?> page) {
      this(page, null);
    }

    public Builder(Page<?> page, Integer blockSize) {
      this(new PageWrapper<>((Page<?>) page, blockSize));
    }

    public Builder(Slice<?> slice) {
      SliceWrapper<?> sliceWrapper = new SliceWrapper<>((Slice<?>) slice);

      create(sliceWrapper.getContent());
      resourcesWrapper.page = sliceWrapper;
    }

    public Builder content(String key, Object value) {
      if (null == resourcesWrapper.content) {
        resourcesWrapper.content = new HashMap<>();
      }

      resourcesWrapper.content.put(key, value);
      return this;
    }

    public Builder search(Object search) {
      resourcesWrapper.search = search;
      return this;
    }

    public Builder page(Object page) {
      resourcesWrapper.page = page;
      return this;
    }

    public ResourcesWrapper build() {
      return resourcesWrapper;
    }

    public Map<String, Object> toMap() {
      Map<String, Object> map = new LinkedHashMap<>();
      if (null != resourcesWrapper.content) {
        map.put("content", resourcesWrapper.content);
      }

      if (null != resourcesWrapper.search) {
        map.put("search", resourcesWrapper.search);
      }

      if (null != resourcesWrapper.page) {
        map.put("page", resourcesWrapper.page);
      }

      return map;
    }

    private void create(Object object) {
      if (null == resourcesWrapper.content) {
        resourcesWrapper.content = new HashMap<>();
      }

      resourcesWrapper.content.put(getResourceKey(object), object);
    }

    private String getResourceKey(Object resources) {
      if (resources instanceof Collection<?>) {
        return RESOURCES_KEY;
      }

      if (resources instanceof Map<?, ?>) {
        return RESOURCES_KEY;
      }

      return RESOURCE_KEY;
    }
  }

}
