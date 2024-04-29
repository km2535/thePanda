/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        custom:
          "0 4px 6px -1px rgba(0, 0, 0, 1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)",
      },
      backgroundSize: {
        "50%": "50% 50%",
        "80%": "80% 80%",
        "100%": "100% 100%",
      },
      backgroundImage: {
        All: "url(./assets/images/panda.png)",
        logo: "url('./assets/images/logo.svg')",
        naverSign: "url('./assets/images/naver-sign-in.png')",
        googleSign: "url('./assets/images/google-sign-in.png')",
        kakaoSign: "url('./assets/images/kakao-sign-in.png')",
        fashion: "url('./assets/images/fashion.png')",
        watch: "url('./assets/images/watch.png')",
        stick: "url('./assets/images/stick.png')",
        noteBook: "url('./assets/images/noteBook.png')",
        chair: "url('./assets/images/chair.png')",
        bed: "url('./assets/images/bed.png')",
        food: "url('./assets/images/food.png')",
        sports: "url('./assets/images/sports.png')",
        healthy: "url('./assets/images/healthy.png')",
        travel: "url('./assets/images/travel.png')",
        naver: "url('./assets/images/naver.png')",
        coupang: "url('./assets/images/coupang.png')",
        gmarket: "url('./assets/images/gmarket.png')",
        kakao: "url('./assets/images/kakao.png')",
        sellerRocket: "url('./assets/images/sellerRocket.png')",
        rocketDelivery: "url('./assets/images/rocketDelivery.png')",
        rocketDirectly: "url('./assets/images/rocketDirectly.png')",
        rocketInstall: "url('./assets/images/rocketInstall.png')",
        rocketLuxury: "url('./assets/images/rocketLuxury.png')",
        rocketFresh: "url('./assets/images/rocketFresh.png')",
      },
      colors: {
        iconsColor: "#001C34",
        brandFontColor: "#677785",
        brandHoverFontColor: "#122028",
        informationColor: "#95A1AB",
        backgroundColor: "#F9FAFB",
        buttonColor: "#647584",
        fontwhiteColor: "#F9FAFB",
      },
    },
  },
  plugins: [],
};
