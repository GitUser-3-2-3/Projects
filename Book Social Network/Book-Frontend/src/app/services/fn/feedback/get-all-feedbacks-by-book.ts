/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import {HttpClient, HttpContext, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {StrictHttpResponse} from '../../strict-http-response';
import {RequestBuilder} from '../../request-builder';

import {PageResponseFeedbackResponse} from '../../models/page-response-feedback-response';

export interface GetAllFeedbacksByBook$Params {
    page?: number;
    size?: number;
    'book-id': number;
}

export function getAllFeedbacksByBook(http: HttpClient, rootUrl: string, params: GetAllFeedbacksByBook$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackResponse>> {
    const rb = new RequestBuilder(rootUrl, getAllFeedbacksByBook.PATH, 'get');
    if (params) {
        rb.query('page', params.page, {});
        rb.query('size', params.size, {});
        rb.path('book-id', params['book-id'], {});
    }

    return http.request(
        rb.build({responseType: 'json', accept: 'application/json', context})
    ).pipe(
        filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
        map((r: HttpResponse<any>) => {
            return r as StrictHttpResponse<PageResponseFeedbackResponse>;
        })
    );
}

getAllFeedbacksByBook.PATH = '/feedbacks/book/{book-id}';
