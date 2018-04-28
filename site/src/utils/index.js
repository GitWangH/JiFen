// 生成1到$count之间的$num个随机数，
export function randomNumber ($count, $num) {
  var count = $count
  var num = $num
  var originalArray = []
  for (var i = 0; i < count; i++) {
    originalArray[i] = i + 1
  }
  originalArray.sort(function () { return 0.5 - Math.random() })
  return originalArray.slice(0, num)
}
