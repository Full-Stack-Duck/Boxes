/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.tsx"],
  theme: {
    fontFamily: {
      'viga': ['"viga"'],
      'quicksand': ['"quicksand"'],
      'nunito': ['"nunito"'],
      'montserrat': ['"montserrat"'],
      'playfair': ['"playfair"'],
      'open': ['Open Sans', 'ui-sans-serif', 'system-ui']
    },
    extend: {
      colors: {
        gray: {
          'lightgray': '#B9B2C6',
          'solidgray': '#585858',
          'purplegray': '#473C4F'
        },
        purple: {
          'darker': '#151126',
          'dark': '#251D43',
          'stroke': '#A693DE',
          'medium': '#6851AC',
          'light': '#B9B2C6',
          'shadow': '#B9B2C6',
          'plano': '#AA84DA'
        },
        green:{
          'hard': '#007849',
          'soft': '#84DAB8'
        },
        pink: {
          'light': '#F0ECFE',
          'soft': '#EAE3FF'
        }
      },
      backgroundImage: {
        'logo': 'url("../assets/logoIcon.svg")'
      }
    },
  },
  plugins: [require('tailwind-scrollbar-hide'), require('@tailwindcss/typography')],
}
