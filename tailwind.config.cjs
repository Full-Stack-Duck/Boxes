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
          '500' : '#251D43'
        }
      }
    },
  },
  plugins: [],
}
