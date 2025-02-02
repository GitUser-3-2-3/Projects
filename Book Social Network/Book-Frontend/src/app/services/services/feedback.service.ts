/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {getAllFeedbacksByBook, GetAllFeedbacksByBook$Params} from '../fn/feedback/get-all-feedbacks-by-book';
import {PageResponseFeedbackResponse} from '../models/page-response-feedback-response';
import {saveFeedback, SaveFeedback$Params} from '../fn/feedback/save-feedback';

@Injectable({providedIn: 'root'})
export class FeedbackService extends BaseService {
    /** Path part for operation `saveFeedback()` */
    static readonly SaveFeedbackPath = '/feedbacks';
    /** Path part for operation `getAllFeedbacksByBook()` */
    static readonly GetAllFeedbacksByBookPath = '/feedbacks/book/{book-id}';

    constructor(config: ApiConfiguration, http: HttpClient) {
        super(config, http);
    }

    /**
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `saveFeedback()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    saveFeedback$Response(params: SaveFeedback$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
        return saveFeedback(this.http, this.rootUrl, params, context);
    }

    /**
     * This method provides access only to the response body.
     * To access the full response (for headers, for example), `saveFeedback$Response()` instead.
     *
     * This method sends `application/json` and handles request body of type `application/json`.
     */
    saveFeedback(params: SaveFeedback$Params, context?: HttpContext): Observable<number> {
        return this.saveFeedback$Response(params, context).pipe(
            map((r: StrictHttpResponse<number>): number => r.body)
        );
    }

    /**
     * This method provides access to the full `HttpResponse`, allowing access to response headers.
     * To access only the response body, use `getAllFeedbacksByBook()` instead.
     *
     * This method doesn't expect any request body.
     */
    getAllFeedbacksByBook$Response(params: GetAllFeedbacksByBook$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackResponse>> {
        return getAllFeedbacksByBook(this.http, this.rootUrl, params, context);
    }

    /**
     * This method provides access only to the response body.
     * To access the full response (for headers, for example), `getAllFeedbacksByBook$Response()` instead.
     *
     * This method doesn't expect any request body.
     */
    getAllFeedbacksByBook(params: GetAllFeedbacksByBook$Params, context?: HttpContext): Observable<PageResponseFeedbackResponse> {
        return this.getAllFeedbacksByBook$Response(params, context).pipe(
            map((r: StrictHttpResponse<PageResponseFeedbackResponse>): PageResponseFeedbackResponse => r.body)
        );
    }

}
