# MyCompose
## Compose UI 종류
### GlideImage
      @Composable
      fun ComposeNetworkImage() {
          Column(
              modifier = Modifier.fillMaxSize()
          ) {
              val imageResourceUrl = "https://www.nintendo.com/kr/character/kirby/assets/img/intro/kirby-star2.png"

              GlideImage(
                  model = imageResourceUrl,
                  contentDescription = "커비1",
              ) {
                  it.listener(object : RequestListener<Drawable> {
                      override fun onLoadFailed(
                          e: GlideException?,
                          model: Any?,
                          target: Target<Drawable>,
                          isFirstResource: Boolean
                      ): Boolean {
                          Log.d("phael", "Image Load Fail")
                          return false
                      }

                      override fun onResourceReady(
                          resource: Drawable,
                          model: Any,
                          target: Target<Drawable>?,
                          dataSource: DataSource,
                          isFirstResource: Boolean
                      ): Boolean {
                          Log.d("phael", "Image Load Success")
                          return true
                      }
                  })
            }
      }
    }