# MyCompose
## Compose UI 종류
### Text
      Text(
        modifier = Modifier.size(300.dp),
        color = Color(0xffff9944), //(color 지정하기, 해시 컬러값 지정하기)
        text = "Hello $name! My $name! Your $name, His $name Her $name",
        fontSize = 30.sp, //(fontSize 사용하기)
        fontWeight = FontWeight.Bold, //(fontWeight 사용하기 (xml의 Text에서 fontStyle 같은거 같다.))
        fontFamily = FontFamily.Monospace,
        letterSpacing = 2.sp, //(자간)
        maxLines = 2, //(xml 처럼 문자열 최대 라인 지정 가능 (최대 2줄까지))
        textDecoration = TextDecoration.Underline, //(밑줄 사용해보기)
        textAlign = TextAlign.Center (textAlign을 TextAlign.Center로 지정 //(xml에서 gravity 같은거),)
    )