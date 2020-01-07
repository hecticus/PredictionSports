import { Injectable } from '@angular/core';
import { Headers, Response, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs';

export class BaseService {

  public static createAuthorizationHeader(): Headers {
      const headers = new Headers();
      headers.append('Authorization', sessionStorage.getItem('token'));
      return headers;
  } //  sessionStorage.getItem('token')

  public static extractData(res: Response) {
      const body = res.json();
      return body.result;
  }

  public static extractDataFull(res: Response) {
      return res.json();
  }


  public static extractHeaders(res: Response) {
      return res.headers;
  }

  public static handleResponse(res: Response) {
      return res.ok;
  }

  public static handleError(error: Response | any) {
      let errMsg: string;
      if (error instanceof Response) {
          if (error.status === 403) {
              return Observable.throw('No posee permisos suficientes para esta accion, contacte con el administrador');
          }

          const body = error.json() || '';
          const err = body.error || JSON.stringify(body);
          errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
      } else {
          errMsg = error.message ? error.message : error.toString();
      }
      return Observable.throw(errMsg);
  }

  public static handleErrorCreate(error: Response | any) {
      if (error instanceof Response) {
          if (error.status === 400) {
              return Observable.throw('Corrija los parámetros inválidos e intente nuevamente');
          }
          if (error.status === 409) {
              return Observable.throw('Corrija los parámetros o dependencias inválidas e intente nuevamente');
          }
      }
      return Observable.throw('Consulte con el administrador del sistema');
  }

  public static handleErrorUpdate(error: Response | any) {
      if (error instanceof Response) {
          if (error.status === 400) {
              return Observable.throw('Corriga los parámetros inválidos e intente nuevamente');
          }
          if (error.status === 401) {
              return Observable.throw('Su contraseña actual es incorrecta');
          }
          if (error.status === 404) {
              return Observable.throw('Registro inexistente');
          }
          if (error.status === 409) {
              return Observable.throw('Corriga los parámetros o dependencias inválidas e intente nuevamente');
          }
      }
      return Observable.throw('Consulte con el administrador del sistema');
  }

  public static handleErrorDelete(error: Response | any) {
      if (error instanceof Response) {
          if (error.status === 409) {
              return Observable.throw('Elimine las dependencias e intente nuevamente');
          }
      }
      return Observable.throw('Consulte con el administrador del sistema');
  }

  public buildRequestOptionsFinder(sort?: string, collection?: string, filter?: {}, pager?:
      {pageIndex: number, pageSize: number}): RequestOptions {
      const params: URLSearchParams = new URLSearchParams();

      if (sort !== undefined) {
          params.set('sort', sort);
      }
      if (collection !== undefined) {
          params.set('collection', collection);
      }
      for (const pos in filter) {
          params.set(filter[pos]['param'], filter[pos]['value'].toString());
      }
      if (pager !== undefined) {
          params.set('index', pager.pageIndex.toString());
          params.set('size', pager.pageSize.toString());
      }

      const requestOptions = new RequestOptions();
      requestOptions.search = params;

      return requestOptions;
  }
}

