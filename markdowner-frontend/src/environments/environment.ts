// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,

  ENDPOINTS:{
    USER_CREATION:'http://localhost:9999/user/create',
    USER_LOGIN:'http://localhost:9999/user/login',
  


    DOC_CREATION:'http://localhost:9998/doc/create',
    DOC_UPDATE:'http://localhost:9998/doc/update',
    DOC_DELETE:'http://localhost:9998/doc/delete',
    DOC_RECENT:'http://localhost:9998/doc/recent',
    DOC_FOR_USER:'http://localhost:9998/doc/all',
    DOC_FETCH:'http://localhost:9998/doc/fetch'

  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
