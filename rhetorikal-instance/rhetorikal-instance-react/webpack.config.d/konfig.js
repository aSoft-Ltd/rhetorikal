config.resolve.modules.push("/media/andylamax/workspace/aSoft/OSS/rhetorikal/code/rhetorikal-instance/rhetorikal-instance-react/build/resources/main")
config.module.rules.push({
    test: /\.(png|jpe?g|gif|svg)$/i,
    use: [
      {
        loader: 'file-loader',
      },
    ],
});
config.devServer = { ...config.devServer, historyApiFallback: true, host: "0.0.0.0" }