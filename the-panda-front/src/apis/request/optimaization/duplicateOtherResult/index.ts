
interface ProcessedData {
  [platform: string]: { [keyword: string]: number };
}

export const duplicateOtherResult = (data: any): ProcessedData => {
  const processedData: ProcessedData = {};

  // 모든 플랫폼의 데이터를 반복하여 중복된 항목과 횟수를 계산합니다.
  Object.keys(data).forEach((platform: string) => {
    const items = data[platform];
    if (typeof items === 'object' && items !== null) {
      Object.values(items).forEach((item: any) => { // 아이템을 반복합니다.
        // 현재 플랫폼의 아이템이 이미 processedData에 포함되어 있는지 확인합니다.
        if (!processedData[platform]) {
          processedData[platform] = {};
        }
          item.forEach((keyword: any) => {
              keyword = (keyword+"").replace( /<[^>]*>/g,'')
          // 다른 플랫폼의 아이템과 비교하여 중복된 항목과 횟수를 계산합니다.
          Object.keys(data).forEach((platformOther: string) => {
            if (platform !== platformOther) {
              const itemsOther = data[platformOther];
              Object.values(itemsOther).forEach((itemd: any) => { // 다른 플랫폼의 아이템을 반복합니다.
                  itemd.forEach((targetKeyword: any) => {
                    targetKeyword = (targetKeyword+"").replace( /<[^>]*>/g,'')
                  if (keyword === targetKeyword) {
                    // 현재 플랫폼의 아이템과 동일한 다른 플랫폼의 아이템을 찾았을 때,
                    // 해당 아이템의 카운트를 증가시킵니다.
                    if (!processedData[platform][keyword]) {
                      processedData[platform][keyword] = 1;
                    } else {
                      processedData[platform][keyword]++;
                    }
                  }
                });
              });
            }
          });
        });
      });
    }
  });
  return processedData;
};
