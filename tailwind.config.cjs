/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.tsx"],
  theme: {
    fontFamily: {
      'viga': ['"viga"'],
      'quicksand': ['"quicksand"'],
      'nunito': ['"nunito"'],
      'montserrat': ['"montserrat']
    },
    extend: {
      colors: {
        gray: {
          'lightgray': '#B9B2C6'
        },
        purple: {
          'darker': '#151126',
          'dark': '#251D43',
          'medium': '#6851AC',
          'light': '#B9B2C6',
          'shadow': '#B9B2C6'
        }
      }
    },
  },
  plugins: [require('tailwind-scrollbar-hide')],
}
