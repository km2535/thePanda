import axios from "axios"

export const duplicateInspect = (totalData: any) => {
    const BASE_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/generator/most-dupuliacte/keyword`;
const headers = {
  "Content-Type": "application/json"
};
let promises: any[] = [];

Object.entries(totalData).forEach(([key, value]) => {
  promises.push(
    axios.post(BASE_URL, value, { headers })
      .then((response) => ({ [key]: response.data }))
      .catch((error) => {
        console.error(`Error while fetching data for ${key}:`, error);
        return { [key]: null }; // 에러가 발생한 경우에도 객체를 반환하도록 수정할 수 있습니다.
      })
  );
});

return Promise.all(promises)
  .then((results) => {
    const result = results.reduce((acc, curr) => ({ ...acc, ...curr }), {});
    return result;
  })
  .catch((error) => {
    console.error("Error:", error);
  });
}

