<!--suppress ALL -->
<template>
  <div>
    <h3>{{passwordResetWelcomeMessage}}</h3><br/>
    <input :placeholder="passwordResetNameTip" @keyup="checkRegisterName" class="form-control email" id="register-name"
           maxlength="100" type="text"/>
    <label class="form-check-label text-danger">{{passwordResetNameErrorTip}}</label>
    <br/>
    <div class="form-inline text-justify-all row">
      <div class="col-sm-7 col-md-7 col-6">
        <input :placeholder="verifyCodeTip" @keyup="validateVerifyCode" class="w-100 form-control email-verify-code"
               id="verify-code" maxlength="6" type="text"/>
      </div>
      <div class="col-sm-5 col-md-5 col-6 text-right">
        <button @click="sendVerifyCode" class="btn btn-outline-info sendVerifyCode btn-block">{{verifyCodeSendTip}}
        </button>
      </div>
    </div>
    <label class="form-check-label text-danger">{{verifyCodeErrorTip}}</label>
    <br/>
    <input :placeholder="newPasswordTip" class="form-control password" id="new-password" maxlength="50"
           type="password">
    <label class="form-check-label text-danger">{{newPasswordErrorTip}}</label>
    <br/>
    <input :placeholder="newPasswordConfirmTip" @keyup="checkPasswordConsistency" class="form-control confirm-password"
           id="confirm-password"
           max="50" type="password">
    <label class="form-check-label text-danger">{{newPasswordConfirmErrorTip}}</label>
    <br/>
    <div class="text-center row">
      <div class="col-6 col-sm-6">
        <a :href="loginPath" class="btn btn-outline-primary btn-block">{{loginTip}}</a>
      </div>
      <div class="col-6 col-sm-6">
        <button @click="resetPassword" class="btn btn-danger btn-block">{{passwordResetTip}}</button>
      </div>
    </div>
  </div>
</template>

<script>/* eslint-disable */
import app from '../App'
import validator from '../../static/js/validator.min'
import {requestResetPassword, requestValidateVerifyCode, requestVerifyCode} from '../api/api'
import utils from '../assets/js/utils'

export default {
  name: 'PasswordReset',
  data () {
    return {
      loginPath: app.data().path.login,
      passwordResetWelcomeMessage: '重置密码',
      passwordResetNameTip: '邮箱',
      passwordResetNameErrorTip: '',
      verifyCodeTip: '收到的验证码',
      verifyCodeSendTip: '发送验证码',
      verifyCodeErrorTip: '',
      newPasswordTip: '请输入你的新密码',
      newPasswordErrorTip: '',
      newPasswordConfirmTip: '请再次输入你的新密码',
      newPasswordConfirmErrorTip: '',
      loginTip: '继续登录',
      passwordResetTip: '重置密码'

    }
  },
  methods: {
    checkRegisterName: function () {
      this.passwordResetNameErrorTip = validator.isEmail($('#register-name').val()) ? '' : '邮箱格式不正确'
    },
    checkPasswordConsistency: function () {
      this.newPasswordConfirmErrorTip = $('#new-password').val() === $('#confirm-password').val() ? '' : '两次输入的密码不一样'
    },
    sendVerifyCode: function () {
      let email = $('#register-name').val()
      if (validator.isEmail(email)) {
        requestVerifyCode(email).then(data => {
          utils.showSuccess(this, data.msg)
        })
      }
    },
    validateVerifyCode: function () {
      let vcode = $('#verify-code').val()
      let email = $('#register-name').val()
      if (vcode.length === 6 && validator.isEmail(email)) {
        requestValidateVerifyCode(email, vcode).then(data => {
          this.verifyCodeErrorTip = data.ok ? '' : '验证码错误'
        })
      }
    },
    resetPassword: function () {
      let vcode = $('#verify-code').val()
      let email = $('#register-name').val()
      let newPassword = $('#confirm-password').val()
      if (validator.isEmpty(email) || validator.isEmpty(vcode) || validator.isEmpty(newPassword)) {
        utils.showWarning(this, '数据不能为空')
      } else if (validator.isEmpty(this.passwordResetNameErrorTip) && validator.isEmpty(this.newPasswordConfirmErrorTip) && validator.isEmpty(this.verifyCodeErrorTip)) {
        requestResetPassword({email: email, newPassword: newPassword, vcode: vcode}).then(data => {
          if (data.ok) {
            window.location = this.loginPath
          } else {
            utils.showError(this, data.msg)
          }
        })
      } else {
        utils.showWarning(this, '格式不正确')
      }
    }
  }
}
</script>

<style scoped>
  @import "../assets/css/style.css";
</style>
