const HOST = `https://markdowner.mooo.com`

export const environment = {
  production: true,

  ENDPOINTS:{
    USER_CREATION:`${HOST}/user/create`,
    USER_LOGIN:`${HOST}/user/login`,
  


    DOC_CREATION:`${HOST}/doc/create`,
    DOC_UPDATE:`${HOST}/doc/update`,
    DOC_DELETE:`${HOST}/doc/delete`,
    DOC_RECENT:`${HOST}/doc/recent`,
    DOC_FOR_USER:`${HOST}/doc/all`,
    DOC_FETCH:`${HOST}/doc/fetch`

  }
};
