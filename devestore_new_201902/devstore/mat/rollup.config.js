import babel from 'rollup-plugin-babel'
import uglify from 'rollup-plugin-uglify'

export default {
  input: 'src/javascript/script.js',
  output: {
    file: '../src/main/resources/static/js/script.js',
    format: 'iife',
    name: 'main'
  },
  plugins: [
    babel(),
    uglify()
  ],
  global: {
    // jquery: '$'  // When using jquery, it must be active.
  }
}
