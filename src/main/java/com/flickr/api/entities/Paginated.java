/*
 * Copyright (C) 2011 by Fabien Barbero
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flickr.api.entities;

import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Fabien Barbero
 */
public final class Paginated<T> implements Iterable<T> {

    private final int page;
    private final int pages;
    private final int perpage;
    private final int total;
    private final List<T> content;

    protected Paginated(JSONObject json, List<T> content) throws JSONException {
        page = json.getInt("page");
        pages = json.getInt("pages");
        if (json.has("per_page")) {
            perpage = json.getInt("per_page");
        } else {
            perpage = json.getInt("perpage");
        }
        total = json.getInt("total");
        this.content = content;
    }

    /**
     * Get the current page.
     *
     * @return The index of the page.
     */
    public int getPageIndex() {
        return page;
    }

    /**
     * Get the number of pages.
     *
     * @return The number of pages.
     */
    public int getPagesCount() {
        return pages;
    }

    /**
     * Get the number of objects per page.
     *
     * @return The number of objects per page.
     */
    public int getPerPage() {
        return perpage;
    }

    /**
     * Get the total number of available objects.
     *
     * @return The number of objects.
     */
    public int getTotalCount() {
        return total;
    }

    /**
     * Indicates if the list is empty
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return total == 0;
    }

    /**
     * Get the paginated values.
     *
     * @return The values.
     */
    public List<T> asList() {
        return content;
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    /**
     * Get a value at a given index
     *
     * @param index The index
     * @return The value
     */
    public T get(int index) {
        return content.get(index);
    }
}
