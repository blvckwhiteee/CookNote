const createApi = (baseUrl) => {
  return new Proxy(
    {},
    {
      get(_, endpoint) {
        return async function (params = "") {
          let url = `${baseUrl}/${endpoint}`;
          if (params && typeof params === "string") {
            url += `/${params}`;
          }
          const response = await fetch(url);
          if (!response.ok) {
            throw new Error(`Request failed with status ${response.status}`);
          }
          return response.json();
        };
      },
    }
  );
};

const API = createApi("http://localhost:8080/api");
export default API;
