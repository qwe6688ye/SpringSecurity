SpringSecurity的初步使用，动态的权限分配：<br>
1、在登录的时候认证用户，使用了自定义的认证器（MyAuthenticationProvider）<br>
2、对资源访问的时候都会查询资源是否需要权限，也就是加载资源所需权限，这里使用自定义的资源权限加载类（MyInvocationSecurityMetadataSourceService）<br>
3、如果资源需要权限访问，那么就把当前认证登录通过的用户的权限和资源所需权限比对，这里也是通过一个自定义的决策器（MyAccessDecisionManager），通过就返回资源，否则失败，返回无权限。<br>




MyAccessDecisionManager和MyInvocationSecurityMetadataSourceService都是在MyFilterSecurityInterceptor中注册的。<br>

MyFilterSecurityInterceptor过滤器是在Security配置中被加入的
<br>