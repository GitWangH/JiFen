<template>
  <div class="reciver-info">
    <p class="user-info"><img src="../../assets/images/icon/icon_dizhi@2x.png" alt="">{{reciverInfo.reciverName}}-{{reciverInfo.reciverTel}}</p>
    <p class="reciver-address">{{reciverInfo.reciverAddress}}</p>
  </div>
</template>
<script>
  export default {
    props: {
      reciverInfo: {
        default: Object
      }
    }
  }
</script>
<style lang="scss">

  .reciver-info {
    background-color: #ffffff;
    padding: $page-margin;
    .user-info {
      font-size: $xlg-size;
      color: $text-black-color;
      img {
        display: inline-block;
        width: 20px;
        height: 20px;
        position: relative;
        top: 4px;
        margin-right: 8px;
      }
    }
    .reciver-address {
      margin-left: 28px;
      font-size: $md-size;
      color: $text-gray-color;
      margin-top: 8px;
      white-space: normal;
      word-break: break-all;
    }
  }
</style>
