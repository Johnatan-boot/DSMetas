/*script do typesctipt*/
/*Exportando uma varavel de ambiente VITE_BACKEND_URL
caso não exista pega o localhost*/
export const BASE_URL = import.meta.env.VITE_BACKEND_URL ?? "http://localhost:8080";