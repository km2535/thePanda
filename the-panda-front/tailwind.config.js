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
      },
      colors: {
        brandFontColor: "#677785",
        brandHoverFontColor: "#122028",
      },
    },
  },
  plugins: [],
};
