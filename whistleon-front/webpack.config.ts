import path from 'path';
import childProcess from 'child_process';
import webpack, { Configuration as WebpackConfiguration } from 'webpack';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import { CleanWebpackPlugin } from 'clean-webpack-plugin';
import ReactRefreshWebpackPlugin from '@pmmmwh/react-refresh-webpack-plugin';
import ForkTsCheckerWebpackPlugin from 'fork-ts-checker-webpack-plugin';
import { BundleAnalyzerPlugin } from 'webpack-bundle-analyzer';
import { Configuration as WebpackDevServerConfiguration } from 'webpack-dev-server';

const isDevelopment = process.env.NODE_ENV !== 'production';

interface Configuration extends WebpackConfiguration {
  devServer?: WebpackDevServerConfiguration;
}

const config: Configuration = {
  name: 'whistle-on-client',
  mode: isDevelopment ? 'development' : 'production',
  devtool: isDevelopment ? 'hidden-source-map' : 'inline-source-map',
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.jsx', '.json', '.css'],
    alias: {
      '~': path.resolve(__dirname, 'src'),
      '~components': path.resolve(__dirname, 'src', 'components/'),
      '~utils': path.resolve(__dirname, 'src', 'utils/'),
      '~typings': path.resolve(__dirname, 'src', 'typings/'),
      '~hooks': path.resolve(__dirname, 'src', 'hooks/'),
      '~pages': path.resolve(__dirname, 'src', 'pages/'),
    },
  },
  entry: {
    'whistle-on-client': path.resolve(__dirname, 'index.tsx'),
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        loader: 'babel-loader',
        options: {
          presets: [
            [
              '@babel/preset-env',
              {
                targets: { browsers: ['last 2 chrome versions'] },
                debug: isDevelopment,
              },
            ],
            '@babel/preset-react',
            '@babel/preset-typescript',
          ],
          env: {
            development: {
              plugins: [
                ['@emotion/babel-plugin', { sourceMap: true }],
                require.resolve('react-refresh/babel'),
              ],
            },
            production: {
              plugins: ['@emotion/babel-plugin'],
            },
          },
        },
        exclude: path.join(__dirname, 'node_modules'),
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
      },
    ],
  },
  plugins: [
    new CleanWebpackPlugin(),
    new ForkTsCheckerWebpackPlugin({
      async: false,
    }),
    new webpack.EnvironmentPlugin({
      NODE_ENV: isDevelopment ? 'development' : 'production',
    }),
    new webpack.ProgressPlugin(),
    new webpack.BannerPlugin({
      banner: () => {
        const commit = childProcess.execSync('git rev-parse --short HEAD');
        const user = childProcess.execSync('git config user.name');
        const email = childProcess.execSync('git config user.email');
        const date = new Date().toLocaleString();

        return (
          `commitVersion: ${commit}` +
          `Build Date: ${date}\n` +
          `Author: ${user}` +
          `Email: ${email}`
        );
      },
    }),
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, 'index.html'),
    }),
  ],
  output: {
    path: path.join(__dirname, 'dist'),
    filename: '[name].bundle.js',
    publicPath: '/dist/',
  },
  devServer: {
    historyApiFallback: true,
    port: 3030,
    devMiddleware: {
      publicPath: '/dist/',
    },
    static: {
      directory: __dirname,
    },
  },
};

if (isDevelopment && config.plugins) {
  config.plugins.push(new webpack.HotModuleReplacementPlugin());
  config.plugins.push(
    new ReactRefreshWebpackPlugin({
      overlay: {
        useURLPolyfill: true,
      },
    })
  );
  config.plugins.push(
    new BundleAnalyzerPlugin({ analyzerMode: 'server', openAnalyzer: false })
  );
}
if (!isDevelopment && config.plugins) {
  config.plugins.push(new webpack.LoaderOptionsPlugin({ minimize: true }));
  config.plugins.push(new BundleAnalyzerPlugin({ analyzerMode: 'static' }));
}

export default config;
