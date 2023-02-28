/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.tsx"],
  theme: {
    fontFamily:{
      'viga' : [ 'viga']
    },
    extend: {
      colors:{
        purple:{
          'dark' : '#251D43',
          'light' : '#B9B2C6'
        }
      }
    },
  },
  plugins: [],
}
