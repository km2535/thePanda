/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      backgroundSize: {
        "50%": "50% 50%",
        "80%": "80% 80%",
        "100%": "100% 100%",
      },
      backgroundImage: {
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
