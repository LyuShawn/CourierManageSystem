const {
	defineConfig
} = require('@vue/cli-service')
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const {
	ElementPlusResolver
} = require('unplugin-vue-components/resolvers')

module.exports = defineConfig({
	publicPath: "./",
	transpileDependencies: true,
	lintOnSave: false,
	// 设置css的主入口文件
	css: {
		loaderOptions: {
			scss: {
				additionalData: '@import "./src/styles/style.scss";'
			}
		}
	},
	configureWebpack: {
		plugins: [
			AutoImport({
				resolvers: [ElementPlusResolver()],
			}),
			Components({
				resolvers: [ElementPlusResolver()],
			}),
		],
	},
	devServer: {
		port:8085,
		proxy: {
			'': {
				target: 'http://101.132.35.228:8083',
				changeOrigin: true,
				ws: false
			}
		}
	}
})