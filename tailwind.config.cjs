/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.tsx"],
  theme: {
    fontFamily:{
      'viga' : [ '"viga"' ],
      'quicksand' : ['"quicksand"']
    },
    extend: {
      colors:{
        purple:{
          'darker' : '#151126',
          'dark' : '#251D43',
          'light' : '#B9B2C6'
        }
      }
    },
  },
  plugins: [],
}
