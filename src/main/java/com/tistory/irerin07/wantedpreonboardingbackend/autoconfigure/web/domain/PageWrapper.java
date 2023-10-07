package com.tistory.irerin07.wantedpreonboardingbackend.autoconfigure.web.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * @author 민경수
 * @description page wrapper
 * @since 2023.10.07
 **********************************************************************************************************************/
public class PageWrapper<T> extends SliceWrapper<T> {

  private static final int DEFAULT_BLOCK_SIZE = 5;


  private final List<PageItem> items = new ArrayList<>();


  public PageWrapper(Page<T> page) {
    this(page, page.getContent(), DEFAULT_BLOCK_SIZE);
  }

  public PageWrapper(Page<T> page, Integer blockSize) {
    this(page, page.getContent(), blockSize);
  }

  public PageWrapper(Page<?> page, List<T> content) {
    this(page, content, DEFAULT_BLOCK_SIZE);
  }

  public PageWrapper(Page<?> page, List<T> content, Integer blockSize) {
    super(page, content);

    if (null == blockSize) {
      blockSize = DEFAULT_BLOCK_SIZE;
    }

    int start = 1;
    int size = page.getTotalPages();
    if (blockSize < page.getTotalPages()) {
      size = blockSize;

      if (this.currentNumber <= blockSize - blockSize / 2) {
        start = 1;
      } else if (this.currentNumber >= page.getTotalPages() - blockSize / 2) {
        start = page.getTotalPages() - blockSize + 1;
      } else {
        start = this.currentNumber - blockSize / 2;
      }
    }

    for (int i = 0; i < size; i++) {
      this.items.add(new PageItem(start + i, (start + i) == this.currentNumber));
    }
  }


  public List<PageItem> getItems() {
    return items;
  }

  public int getTotalPages() {
    return ((Page<T>) page).getTotalPages();
  }

  public long getTotalElements() {
    return ((Page<T>) page).getTotalElements();
  }


  public static class PageItem {

    private final int number;
    private final boolean current;


    PageItem(int number, boolean current) {
      this.number = number;
      this.current = current;
    }


    public int getNumber() {
      return this.number;
    }

    public boolean isCurrent() {
      return this.current;
    }

  }

}
