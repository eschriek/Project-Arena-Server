function arenaserver(){var l='',F='" for "gwt:onLoadErrorFn"',D='" for "gwt:onPropertyErrorFn"',n='"><\/script>',p='#',hb='&',r='/',Eb='0439CE7B8E838D6D56106354FD7A3BC8.cache.html',gc='086B0D34C3516E5A8CCC6E05E27E4C3F.cache.html',Fb='23BE6562A9D96BF3578BA14575F5AC43.cache.html',bc='2C7B6E7B7E2451943FEBEC3423E19D3F.cache.html',ic='66A8A40243A42FDFC0C1061D5A475423.cache.html',fc='6C295DFFE44F6F5F25CC42E35FF3A7C5.cache.html',Db='7E51E31D5CC611B073E32DCD9C03E840.cache.html',kc='<script defer="defer">arenaserver.onInjectionDone(\'arenaserver\')<\/script>',nc='<script id="',A='=',q='?',ac='A8E7A85D7EB62FB302BF2CDCEDB75ABD.cache.html',hc='ADF4F1A45DB898DCC315EF4352CBF87F.cache.html',C='Bad handler "',Bb='C4633D6BBC49F4BBE8A4FCFEB229DA26.cache.html',jc='DOMContentLoaded',cc='E0F2A0E77CAE601322260C9136CE48C1.cache.html',ec='E7B4DF10E9AC259B93A30CCD173A62FD.cache.html',o='SCRIPT',lb='Unexpected exception in locale detection, using default: ',kb='_',ib='__gwt_Locale',mc='__gwt_marker_arenaserver',m='arenaserver',s='base',nb='begin',cb='bootstrap',u='clear.cache.gif',z='content',jb='default',lc='end',ub='gecko',vb='gecko1_8',yb='gwt.hybrid',E='gwt:onLoadErrorFn',B='gwt:onPropertyErrorFn',y='gwt:property',zb='hosted.html?arenaserver',tb='ie6',sb='ie8',ab='iframe',t='img',bb="javascript:''",xb='loadExternalRefs',fb='locale',gb='locale=',v='meta',eb='moduleRequested',dc='moduleStartup',rb='msie',w='name',Cb='nl',ob='opera',db='position:absolute;width:0;height:0;border:none',qb='safari',Ab='selectingPermutation',x='startup',wb='unknown',mb='user.agent',pb='webkit';var pc=window,k=document,oc=pc.__gwtStatsEvent?function(a){return pc.__gwtStatsEvent(a)}:null,dd,zc,uc,tc=l,Cc={},gd=[],cd=[],sc=[],Fc,bd;oc&&oc({moduleName:m,subSystem:x,evtGroup:cb,millis:(new Date()).getTime(),type:nb});if(!pc.__gwt_stylesLoaded){pc.__gwt_stylesLoaded={}}if(!pc.__gwt_scriptsLoaded){pc.__gwt_scriptsLoaded={}}function yc(){var b=false;try{b=pc.external&&(pc.external.gwtOnLoad&&pc.location.search.indexOf(yb)==-1)}catch(a){}yc=function(){return b};return b}
function Bc(){if(dd&&zc){var c=k.getElementById(m);var b=c.contentWindow;if(yc()){b.__gwt_getProperty=function(a){return vc(a)}}arenaserver=null;b.gwtOnLoad(Fc,m,tc);oc&&oc({moduleName:m,subSystem:x,evtGroup:dc,millis:(new Date()).getTime(),type:lc})}}
function wc(){var j,h=mc,i;k.write(nc+h+n);i=k.getElementById(h);j=i&&i.previousSibling;while(j&&j.tagName!=o){j=j.previousSibling}function f(b){var a=b.lastIndexOf(p);if(a==-1){a=b.length}var c=b.indexOf(q);if(c==-1){c=b.length}var d=b.lastIndexOf(r,Math.min(c,a));return d>=0?b.substring(0,d+1):l}
;if(j&&j.src){tc=f(j.src)}if(tc==l){var e=k.getElementsByTagName(s);if(e.length>0){tc=e[e.length-1].href}else{tc=f(k.location.href)}}else if(tc.match(/^\w+:\/\//)){}else{var g=k.createElement(t);g.src=tc+u;tc=f(g.src)}if(i){i.parentNode.removeChild(i)}}
function ad(){var f=document.getElementsByTagName(v);for(var d=0,g=f.length;d<g;++d){var e=f[d],h=e.getAttribute(w),b;if(h){if(h==y){b=e.getAttribute(z);if(b){var i,c=b.indexOf(A);if(c>=0){h=b.substring(0,c);i=b.substring(c+1)}else{h=b;i=l}Cc[h]=i}}else if(h==B){b=e.getAttribute(z);if(b){try{bd=eval(b)}catch(a){alert(C+b+D)}}}else if(h==E){b=e.getAttribute(z);if(b){try{Fc=eval(b)}catch(a){alert(C+b+F)}}}}}}
function rc(a,b){return b in gd[a]}
function qc(a){var b=Cc[a];return b==null?null:b}
function fd(d,e){var a=sc;for(var b=0,c=d.length-1;b<c;++b){a=a[d[b]]||(a[d[b]]=[])}a[d[c]]=e}
function vc(d){var e=cd[d](),b=gd[d];if(e in b){return e}var a=[];for(var c in b){a[b[c]]=c}if(bd){bd(d,a,e)}throw null}
var xc;function Ac(){if(!xc){xc=true;var a=k.createElement(ab);a.src=bb;a.id=m;a.style.cssText=db;a.tabIndex=-1;k.body.appendChild(a);oc&&oc({moduleName:m,subSystem:x,evtGroup:dc,millis:(new Date()).getTime(),type:eb});a.contentWindow.location.replace(tc+ed)}}
cd[fb]=function(){try{var g;if(g==null){var b=location.search;var h=b.indexOf(gb);if(h>=0){var e=b.substring(h);var c=e.indexOf(A)+1;var d=e.indexOf(hb);if(d==-1){d=e.length}g=e.substring(c,d)}}if(g==null){g=qc(fb)}if(g==null){g=pc[ib]}if(g==null){return jb}while(!rc(fb,g)){var f=g.lastIndexOf(kb);if(f==-1){g=jb;break}else{g=g.substring(0,f)}}return g}catch(a){alert(lb+a);return jb}};gd[fb]={'default':0,nl:1};cd[mb]=function(){var d=navigator.userAgent.toLowerCase();var b=function(a){return parseInt(a[1])*1000+parseInt(a[2])};if(d.indexOf(ob)!=-1){return ob}else if(d.indexOf(pb)!=-1){return qb}else if(d.indexOf(rb)!=-1){if(document.documentMode>=8){return sb}else{var c=/msie ([0-9]+)\.([0-9]+)/.exec(d);if(c&&c.length==3){var e=b(c);if(e>=6000){return tb}}}}else if(d.indexOf(ub)!=-1){var c=/rv:([0-9]+)\.([0-9]+)/.exec(d);if(c&&c.length==3){if(b(c)>=1008)return vb}return ub}return wb};gd[mb]={gecko:0,gecko1_8:1,ie6:2,ie8:3,opera:4,safari:5};arenaserver.onScriptLoad=function(){if(xc){zc=true;Bc()}};arenaserver.onInjectionDone=function(){dd=true;oc&&oc({moduleName:m,subSystem:x,evtGroup:xb,millis:(new Date()).getTime(),type:lc});Bc()};wc();var ed;if(yc()){if(pc.external.initModule&&pc.external.initModule(m)){pc.location.reload();return}ed=zb}ad();oc&&oc({moduleName:m,subSystem:x,evtGroup:cb,millis:(new Date()).getTime(),type:Ab});if(!ed){try{fd([jb,tb],Bb);fd([Cb,ob],Db);fd([jb,sb],Eb);fd([Cb,vb],Fb);fd([Cb,qb],ac);fd([Cb,ub],bc);fd([jb,ob],cc);fd([Cb,tb],ec);fd([jb,vb],fc);fd([jb,qb],gc);fd([jb,ub],hc);fd([Cb,sb],ic);ed=sc[vc(fb)][vc(mb)]}catch(a){return}}var Ec;function Dc(){if(!uc){uc=true;Bc();if(k.removeEventListener){k.removeEventListener(jc,Dc,false)}if(Ec){clearInterval(Ec)}}}
if(k.addEventListener){k.addEventListener(jc,function(){Ac();Dc()},false)}var Ec=setInterval(function(){if(/loaded|complete/.test(k.readyState)){Ac();Dc()}},50);oc&&oc({moduleName:m,subSystem:x,evtGroup:cb,millis:(new Date()).getTime(),type:lc});oc&&oc({moduleName:m,subSystem:x,evtGroup:xb,millis:(new Date()).getTime(),type:nb});k.write(kc)}
arenaserver();