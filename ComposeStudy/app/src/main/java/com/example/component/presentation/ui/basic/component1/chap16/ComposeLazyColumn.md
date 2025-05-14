# MyCompose
## Compose UI 종류
### LazyColumn
      @Composable
      fun ComposeCatalog() {
          // LazyColumn : RecyclerView와 비슷한 역할 (orientation Vertical, Horizontal은 LazyRow)
          LazyColumn {
              items(items.size) { index ->
                  CatalogItem(itemData = items[index])
              }
          }
      }
      
      @Composable
      fun CatalogItem(
          itemData: ItemData = items[0]
      ) {
          Card(
              elevation = CardDefaults.elevatedCardElevation(8.dp),
              modifier = Modifier.padding(16.dp)
          ) {
              Column(
                  modifier = Modifier.padding(8.dp)
              ) {
                  Image(
                      painter = painterResource(id = itemData.imageResource),
                      contentDescription = "카테고리 이미지"
                  )
      
                  Text(
                      text = itemData.title,
                      fontSize = 24.sp
                  )
      
                  Spacer(
                      modifier = Modifier.size(8.dp)
                  )
      
                  Text(
                      text = itemData.description
                  )
      
              }
          }
      }
