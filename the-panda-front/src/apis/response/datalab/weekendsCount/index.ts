import axios from "axios";

interface DataItem {
    period: string;
    ratio: number;
}

function parseDate(dateString: string): Date {
    const [year, month, day] = dateString.split('-').map(Number);
    return new Date(year, month - 1, day);
}

function getDayOfWeek(date: Date): string {
    const days = ['일', '월', '화', '수', '목', '금', '토'];
    return days[date.getDay()];
}

const WeekSearchCountAPI = async (
    keyword: string,
    categoryId: string,
    ago: number,
    type: string,
    total_qc_cnt: number
): Promise<Record<string, number> | undefined> => {
    const API_DOMAIN = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/admin/naver/get-datalab/search-result?keyword=${keyword}&categoryId=${categoryId}&ago=${ago}&type=${type}`;

    try {
        const response = await axios.get(API_DOMAIN);
        const data: DataItem[] | undefined = response?.data?.results[0]?.data;

        if (!data || data.length === 0) {
            return undefined;
        }

        const sumByDay: Record<string, number> = {};

        data.forEach((item: { period: string; ratio: number }) => {
            const date = parseDate(item.period);
            const dayOfWeek = getDayOfWeek(date);
            const count = Math.round((item.ratio / data[data.length - 1].ratio) * (total_qc_cnt/30));

            if (!sumByDay[dayOfWeek]) {
                sumByDay[dayOfWeek] = 0;
            }
            sumByDay[dayOfWeek] += count;
        });

        return sumByDay;
    } catch (error) {
        console.error("Error fetching data:", error);
        return undefined;
    }
};

export default WeekSearchCountAPI;
