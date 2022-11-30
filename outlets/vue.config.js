
module.exports = {
  publicPath: './', // 基本路径
  outputDir: 'dist', // 输出文件目录
  assetsDir: './assets',
  indexPath: 'index.html',
  filenameHashing: true, // 生成的静态资源在它们的文件名中包含了 hash 以便更好的控制缓存
  lintOnSave: false, // eslint-loader 是否在保存的时候检查
  //配置跨域
  // devServer:{
	 //  proxy: {
		//   '':{
		// 	  target: 'https://api.shawnxixi.icu',
		// 	  changeOrigin:true,
		//   }
	 //  }
  // }
}