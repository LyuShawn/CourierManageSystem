// 登录时的输入文本正则验证

export function CKEmail(data){
	let reg = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,15}$/;// 验证密码 6至15位的字母+数字
	return !reg.test(data)?true:false
}
export function CKPass(data){
	let reg = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,15}$/;// 验证密码 6至15位的字母+数字
	return !reg.test(data)?true:false
}