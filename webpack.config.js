const path = require('path');
const Webpack = require('webpack');
const RefreshWebpackPlugin = require('@pmmmwh/react-refresh-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
  mode: 'development',
  devtool: 'eval',
  resolve: {
    extensions: ['.js', '.jsx', '.scss', '.css'],
  },
  entry: {
    whistle_on: './src/index.js'
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        loader: 'babel-loader',
        options: {
          presets: [
            ['@babel/preset-env', {
              targets: {
                browsers: ['> 5% in KR', 'last 2 chrome versions'],
              },
            }],
            '@babel/preset-react'
          ],
          plugins: [
            'react-refresh/babel'
          ],
        },
      },
      {
        test: /\.s?css$/,
        use: [
          MiniCssExtractPlugin.loader, 
          'css-loader', 
          {
            loader: 'sass-loader',
            options: {
              implementation: require('sass')
            }
          }
        ],
      },
      {
        test: /\.(png|jpe?g|gif)$/i,
        use: [
          {
            loader: 'url-loader',
            options: {
              name: '[name].[ext]',
              outputPath: 'assets/images',
              publicPath: '/dist/assets/images/',
              esModule: false,
              limit: 4000
            }
          },
        ],
      },
    ]
  },
  plugins: [
    new Webpack.ProgressPlugin(),
    new Webpack.LoaderOptionsPlugin({ debug: true }),
    new HtmlWebpackPlugin({ template: './index.html' }),
    new MiniCssExtractPlugin({ filename: 'css/style.css'}),
    new RefreshWebpackPlugin(),
  ],
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'js/[name].bundle.js',
    publicPath: '/dist/'
  },
  devServer: {
    contentBase: path.join(__dirname, 'dist'),
    hot: true,
  }
};