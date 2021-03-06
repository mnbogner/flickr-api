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
package com.flickr.api;

import com.flickr.api.entities.Contact;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.ContactsResponse;

/**
 * Service used to get the contacts informations.
 *
 * @author Fabien Barbero
 */
public final class ContactsService extends FlickrService {

    ContactsService(OAuthHandler oauthHandler) {
        super(oauthHandler);
    }

    /**
     * Get a list of contacts for the calling user
     *
     * @param perPage Number of photos to return per page. The maximum allowed value is 1000
     * @param page The page of results to return
     * @return The contacts
     * @throws FlickrException Error getting the contacts
     */
    public Paginated<Contact> getContacts(int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.contacts.getList");
        args.addParam("per_page", perPage);
        args.addParam("page", page);
        return doGet(args, ContactsResponse.class).getPaginated();
    }

    /**
     * Get the contact list for a user
     *
     * @param user The user to fetch the contact list for
     * @param perPage Number of photos to return per page. The maximum allowed value is 1000
     * @param page The page of results to return
     * @return The contacts
     * @throws FlickrException Error getting the contacts
     */
    public Paginated<Contact> getPublicContacts(BaseUser user, int perPage, int page) throws FlickrException {
        CommandArguments args = new CommandArguments("flickr.contacts.getPublicList");
        args.addParam("per_page", perPage);
        args.addParam("page", page);
        args.addParam("user_id", user.getId());
        return doGet(args, ContactsResponse.class).getPaginated();
    }
}
