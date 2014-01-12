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
package com.flickr.api.services;

import com.flickr.api.FlickrService;
import com.flickr.api.CommandArguments;
import com.flickr.api.FlickrServiceException;
import com.flickr.api.OAuthHandler;
import com.flickr.api.entities.BaseUser;
import com.flickr.api.entities.Comment;
import com.flickr.api.entities.CommentsResponse;
import com.flickr.api.entities.Paginated;
import com.flickr.api.entities.PaginatedPhotosInSetResponse;
import com.flickr.api.entities.Photo;
import com.flickr.api.entities.Photoset;
import com.flickr.api.entities.PhotosetInfos;
import com.flickr.api.entities.PhotosetInfosResponse;
import com.flickr.api.entities.PhotosetResponse;
import com.flickr.api.entities.PhotosetsResponse;
import java.util.List;
import org.apache.http.client.HttpClient;

/**
 *
 * @author Fabien Barbero
 */
public class PhotosetsService extends FlickrService {

    public PhotosetsService(OAuthHandler oauthHandler, HttpClient client) {
        super(oauthHandler, client);
    }

    public Photoset getPhotosetById(String id) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photosets.getInfo", false);
        args.put("photoset_id", id);
        return doGet(args, PhotosetResponse.class).getPhotoset();
    }

    public Paginated<Photoset> getPhotosets(BaseUser user, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photosets.getList", false);
        args.put("user_id", user.getId());
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PhotosetsResponse.class).getPhotosets();
    }

    public Paginated<Photo> getPhotos(Photoset photoset, int perPage, int page) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photosets.getPhotos", false);
        args.put("photoset_id", photoset.getId());
        args.put("per_page", perPage);
        args.put("page", page);
        return doGet(args, PaginatedPhotosInSetResponse.class).getPhotos();
    }

    public PhotosetInfos getInfos(Photoset photoset) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photosets.getInfo", false);
        args.put("photoset_id", photoset.getId());
        return doGet(args, PhotosetInfosResponse.class).getInfos();
    }

    public List<Comment> getComments(Photoset photoset) throws FlickrServiceException {
        CommandArguments args = new CommandArguments("flickr.photosets.comments.getList", false);
        args.put("photoset_id", photoset.getId());
        return doGet(args, CommentsResponse.class).getComments();
    }
}