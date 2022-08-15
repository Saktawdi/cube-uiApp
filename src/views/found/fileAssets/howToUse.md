<p align="center">
	<img alt="logo" src="https://s1.328888.xyz/2022/07/26/DV3J6.png" style="width:128px;border-radius:68px">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Sakta_wdi</h1>
<h4 align="center">游戏key管理系统API文档V0.1.0</h4>
<p align="center">更新日期：2022年8月14日</p>
<p align="center">
	<a href="https://github.com/Saktawdi"><img src="https://img.shields.io/badge/Vue3.X-%E5%89%8D%E7%AB%AF-important"></a>
</p>

# 1.获取所有key

<div class="type">请求类型：POST</div>

API:

```
/api/v1/pri/gameKey/getKeys
```

请求参数



| 参数名     | 是否必须 | 参数类型 | 说明 |
| ---------- | -------- | -------- | ---- |
| secret_key | **是**   | String   | 密钥 |

请求实例

```js
{
	"secret_key":51DW51ASA1D6ASD6
}
```

响应数据示例

```js
{
    "success": true,
    "message": "操作成功",
    "data": "[[{"ifUsed":"false"},{"color":"#0000"},{"num":"5"},{"thingID":"666"},{"key":"e3f1d-9a75-40fe-bb06-aeaee"}],[{"ifUsed":"false"},{"color":"#0000"},{"num":"5"},{"thingID":"666"},{"key":"c4bf7-7169-490d-a52d-a6ba0"}],[{"ifUsed":"false"},{"color":"#0000"},{"num":"5"},{"thingID":"666"},{"key":"e8248-2706-4fa0-bc09-40504"}],[{"ifUsed":"false"},{"color":"#0000"},{"num":"5"},{"thingID":"666"},{"key":"b3e67-798a-4283-b2c1-36c7a"}]]",
    "code": "0"
}
```



# 2.更新keys数据

<div class="type">请求类型：POST</div>

API:

```
/api/v1/pri/gameKey/updataKeysApi
```

请求参数

| 参数名     | 是否必须 | 参数类型 | 说明    |
| ---------- | -------- | -------- | ------- |
| secret_key | **是**   | String   | 密钥    |
| list_json  | 是       | Sting    | kes数据 |

请求实例

```js
{
	"secret_key":51DW51ASA1D6ASD6,
     "list_json":[{"ifUsed":"true","key":"b3e67-798a-4283-b2c1-36c7a"}]
}
```

响应数据示例

```js
{
    "success": true,
    "message": "操作成功",
    "data": "更新成功",
    "code": "0"
}
```

