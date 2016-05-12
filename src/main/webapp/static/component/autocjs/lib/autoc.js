(function ( global, factory ) {
    'use strict';

    if ( typeof define === 'function' && define.amd ) {
        // AMD (Register as an anonymous module)
        define('autocjs', [ 'jquery' ], factory( global, $ ) );
    }
    else {
        
        if ( typeof define === 'function' && define.cmd ) {
            // CMD (Register as an anonymous module)
            define( 'autocjs', function ( require, exports, module ) {
                module.exports = factory( global, require( 'jquery' ) );
            } );
        }
        else {
            
            if ( typeof exports === 'object' ) {
                // Node/CommonJS
                module.exports = factory( global, require( 'jquery' ) );
            }
            else {
                // Browser globals
                factory( global, jQuery );
            }
        }
    }
}( typeof window !== "undefined" ? window : this, function ( window, $ ) {
    'use strict';
    
    var CLS_SHOW = 'toc-show',
        CLS_HIDE = 'toc-hide',
        WRAP = '<div id="toc" class="toc toc-hide"></div>',
        TITLE = '<h3 class="toc-title" id="toc-title">Table of Contents</h3>',
        BAR = '<div class="toc-bar"></div>',
        SWITCH = '<h2 class="toc-switch" class="toc-switch" title="Toggle Menu">&#926;</h2>',
        TOP = '<a class="toc-top" id="toc-top" href="#top">TOP</a>',
        BODY = '<nav id="toc-bd" class="toc-bd"></nav>',
        LIST = '<ol id="toc-list" class="toc-list"></ol>',
        SUB_LIST = '<ol class="toc-sub-list"></ol>',
        ITEM = '<li class="toc-item"></li>',
        LINK = '<a></a>',
        CHAPTER = '<em class="toc-chapter"></em>',
        OVERLAY = '<div id="toc-overlay" class="toc-overlay toc-hide"></div>',
        ANCHORS = 'h1,h2,h3,h4,h5,h6',
        PREFIX = 'anchor',
        $article = null,
        $anchors = null,
        $wrap = null,
        $title = null,
        $bar = null,
        $switch = null,
        $top = null,
        $body = null,
        $list = null,
        $overlay = null,
        _uid = -1;
    
    /**
     * 生成唯一的 id
     *
     * @method guid
     * @param {String} [prefix] - 可选，默认生成数字ID，设置了 prefix 则生成字符串ID
     * @returns {Number|String}
     */
    function guid ( prefix ) {
        var id;
        
        _uid += 1;
        id = prefix ? prefix + '-' + _uid : _uid;
        
        return id;
    }


    var AutocJS = {
        version: '0.1.2',
        /**
         * Default Configration
         */
        defaults: {
            article: '#article',
            Templates: {
                WRAP: WRAP,
                TITLE: TITLE,
                BAR: BAR,
                SWITCH: SWITCH,
                TOP: TOP,
                BODY: BODY,
                LIST: LIST,
                SUB_LIST: LIST,
                ITEM: ITEM,
                LINK: LINK,
                CHAPTER: CHAPTER,
                OVERLAY: OVERLAY
            },
            selector: ANCHORS,
            prefix: PREFIX
        },
        attributes: {},
        /**
         * 设置部件属性
         *
         * @param config
         * @returns {AutocJS}
         */
        set: function ( config ) {

            if ( $.isPlainObject( config ) ) {
                $.extend( this.attributes, config );
            }

            return this;
        },
        /**
         * 初始化程序
         *
         * @param {Object} config - 配置信息
         * @param {String|HTMLElement} config.article
         * @param {String} [config.selector]
         * @param {String} [config.prefix]
         */
        init: function ( config ) {
            this.set( this.defaults )
                .set( config )
                ._init()
                .render()
                .attachEvents();

            return this;
        },
        /**
         * 初始化 DOM 部件
         *
         * @returns {AutocJS}
         * @private
         */
        _init: function () {
            var attrs = this.attributes,
                Templates = attrs.Templates;

            // 获得文章内容的 DOM 节点
            $article = $( attrs.article );

            // 获得文章中所有的标题
            $anchors = $article.find( attrs.selector );

            // 初始化 DOM 部件
            $wrap = $( Templates.WRAP );
            $title = $( Templates.TITLE );
            $bar = $( Templates.BAR );
            $switch = $( Templates.SWITCH );
            $top = $( Templates.TOP );
            $body = $( Templates.BODY );
            $list = $( Templates.LIST );
            $overlay = $( Templates.OVERLAY );

            return this;
        },
        /**
         * 绘制界面框架
         *
         * @returns {AutocJS}
         */
        render: function () {

            // 绘制head
            $bar.append( $switch ).append( $top );

            // 绘制body
            $body.append( $list );

            // 绘制完整的导航
            $wrap.append( $title ).append( $body ).append( $bar );

            // 将导航和遮罩层添加到页面
            $( document.body ).append( $wrap ).append( $overlay );

            // 绘制具体的菜单项
            this.renderChapters();

            // 全部绘制完成，再显示完整的菜单
            $wrap.removeClass( CLS_HIDE );

            // 更新菜单的高度
            this.updateLayout();

            return this;
        },
        /**
         * 绘制章节内容
         *
         * @returns {AutocJS}
         */
        renderChapters: function () {
            var chapters = this.getChapters();

            $( chapters ).each( function ( i, chapter ) {

                var $item = $( ITEM ),
                    $parent = null,
                    $link = $( LINK ),
                    chapterText = '',
                    chapterCount = 0,
                    $chapter = $( CHAPTER ),
                    $sublist = $( '#toc-list-' + chapter.pid );

                $link.attr( {
                    id: 'toc-link-' + chapter.id,
                    href: '#' + chapter.value
                } ).html( chapter.text );

                $item.attr( {
                    'id': 'toc-item-' + chapter.id,
                    'title': chapter.text
                } ).append( $link );

                if ( chapter.pid === -1 ) {
                    $list.append( $item );
                    chapterCount = $item.index() + 1;
                    chapterText = chapterCount;
                }
                else {

                    $parent = $( '#toc-item-' + chapter.pid );

                    // 没有绘制子菜单，则绘制它
                    if ( !$sublist[ 0 ] ) {
                        $sublist = $( SUB_LIST ).attr( 'id', 'toc-list-' + chapter.pid );

                        $parent.append( $sublist );
                    }

                    $sublist.append( $item );

                    // 绘制章节索引
                    chapterCount = $item.index() + 1;
                    chapterText = $parent.find( '.toc-chapter' ).html() + '.' + chapterCount;
                }

                // 绘制链接
                $chapter.attr( 'data-chapter', chapterCount ).html( chapterText );
                $chapter.insertBefore( $link );
            } );

            return this;
        },
        /**
         * 显示菜单
         *
         * @returns {AutocJS}
         */
        show: function () {
            $overlay.removeClass( CLS_HIDE );

            $wrap.animate( {
                left: 0
            }, 150, function () {
                $wrap.addClass( CLS_SHOW );
            } );

            return this;
        },
        /**
         * 隐藏菜单
         */
        hide: function () {

            $wrap.animate( {
                left: -300
            }, 150, function () {
                $overlay.addClass( CLS_HIDE );
                $wrap.removeClass( CLS_SHOW );
            } );

            return this;
        },
        /**
         * 隐藏/显示导航
         */
        toggle: function () {

            if ( $wrap.hasClass( CLS_SHOW ) ) {
                this.hide()
            }
            else {
                this.show();
            }

            return this;
        },
        /**
         * 更新菜单界面高度
         *
         * @returns {AutocJS}
         */
        updateLayout: function () {
            var wrapHeight = $wrap[ 0 ].offsetHeight,
                titleHeight = $title[ 0 ].offsetHeight;

            $body.height( wrapHeight - titleHeight );

            return this;
        },
        /**
         * 获得文章完整的章节索引数据
         *
         * @returns {Array}
         */
        getChapters: function () {
            var self = this,
                chapters = [],
                prevNum = 1,
                level = 0;

            // 获得目录索引信息
            $anchors.each( function ( i, anchor ) {
                var $anchor = $( anchor ),
                    curNum = parseInt( $anchor[ 0 ].tagName.toUpperCase().replace( /[H]/ig, '' ), 10 ),
                    pid = -1;

                $anchor.attr( 'id', guid( self.attributes.prefix ) );

                // 1.（父标题，子标题）：当前标题的序号 > 前一个标题的序号
                if ( curNum > prevNum ) {
                    level += 1;

                    // 第一层级的 pid 是 -1
                    if ( level === 1 ) {
                        pid = -1;
                    }
                    else {
                        pid = i - 1;
                    }
                }
                else {
                    // 2.（同级标题，同级标题）
                    // A. 当前标题的序号 === 前一个标题的序号
                    // B. 当前标题的序号 < 前一个标题的序号 && 当前标题的序号 > 等级
                    if ( curNum === prevNum || (curNum < prevNum && curNum > level) ) {

                        // H1 的层级肯定是 1
                        if ( curNum === 1 ) {
                            level = 1;

                            pid = -1;
                        }
                        else {
                            pid = chapters[ i - 1 ].pid;
                        }
                    }
                    else {
                        // 3.（子标题，父级标题）：当前标题的序号 < 前一个标题的序号
                        if ( curNum <= level ) {

                            // H1 的层级肯定是 1
                            if ( curNum === 1 ) {
                                level = 1;
                            }
                            else {
                                level = level - (prevNum - curNum);
                            }

                            // 第一级的标题
                            if ( level === 1 ) {
                                pid = -1
                            }
                            else {
                                // 最大只有5系的差距
                                // 虽然看上去差点，不过能工作啊
                                switch ( prevNum - curNum ) {
                                    case 1:
                                        pid = chapters[ chapters[ i - 1 ].pid ].pid;
                                        break;
                                    case 2:
                                        pid = chapters[ chapters[ chapters[ i - 1 ].pid ].pid ].pid;
                                        break;
                                    case 3:
                                        pid = chapters[ chapters[ chapters[ chapters[ i - 1 ].pid ].pid ].pid ].pid;
                                        break;
                                    case 4:
                                        pid = chapters[ chapters[ chapters[ chapters[ chapters[ i - 1 ].pid ].pid ].pid ].pid ].pid;
                                        break;
                                    case 5:
                                        pid = chapters[ chapters[ chapters[ chapters[ chapters[ chapters[ i - 1 ].pid ].pid ].pid ].pid ].pid ].pid;
                                        break;
                                }
                            }
                        }
                    }
                }

                prevNum = curNum;

                chapters.push( {
                    id: i,
                    level: level,
                    text: $anchor.html(),
                    value: $anchor.attr( 'id' ),
                    tag: anchor.tagName,
                    pid: pid
                } );
            } );

            return chapters;
        },
        /**
         * 获得所有的标题标签
         *
         * @returns {HTMLElement}
         */
        getAnchors: function(){
            return $anchors;
        },
        /**
         * 获得菜单的根节点 DOM
         *
         * @returns {HTMLElement}
         */
        getWrap: function(){
            return $wrap;
        },
        /**
         * 获得菜单的标题节点
         *
         * @returns {HTMLElement}
         */
        getTitle: function(){
            return $title;
        },
        /**
         * 获得菜单的侧边控制栏
         *
         * @returns {HTMLElement}
         */
        getBar: function(){
            return $bar;
        },
        /**
         * 获得开关按钮
         *
         * @returns {HTMLElement}
         */
        getSwitchButton: function(){
            return $switch;
        },
        /**
         * 获得返回顶部按钮
         *
         * @returns {HTMLElement}
         */
        getTopButton: function(){
            return $top;
        },
        /**
         * 获得菜单内容节点
         *
         * @returns {HTMLElement}
         */
        getBody: function(){
            return $body;
        },
        /**
         * 获得菜单节点
         *
         * @returns {HTMLElement}
         */
        getList: function(){
            return $list;
        },
        /**
         * 获得遮罩层节点
         *
         * @returns {HTMLElement}
         */
        getOverlay: function(){
            return $overlay;
        },
        /**
         * 给导航菜单的各个 DOM 部件绑定事件处理器
         *
         * @returns {AutocJS}
         */
        attachEvents: function () {

            // 点击目录标题，隐藏/显示目录导航
            $switch.on( 'click', this._onSwitchClick );

            // 点击TOP链接，返回页面顶部
            $top.on( 'click', this._onTopClick );

            // 点击导航，定位文章，收起导航
            $list.delegate( 'li', 'click', this._onChapterClick );

            // 点击遮罩层，收起导航
            $overlay.on( 'click', this._onOverlayClick );

            $( window ).on( 'resize', this._onWindowResize );

            return this;
        },
        /**
         *
         * @param evt
         * @returns {AutocJS}
         * @private
         */
        _onSwitchClick: function(evt){
            AutocJS.toggle();

            evt.stopPropagation();
            evt.preventDefault();

            return AutocJS;
        },
        /**
         *
         * @param evt
         * @returns {AutocJS}
         * @private
         */
        _onTopClick: function(evt){
            AutocJS.hide();

            evt.stopPropagation();
            evt.preventDefault();

            return AutocJS;
        },
        /**
         *
         * @param evt
         * @returns {AutocJS}
         * @private
         */
        _onChapterClick: function(){
            AutocJS.hide();

            return AutocJS;
        },
        /**
         *
         * @param evt
         * @returns {AutocJS}
         * @private
         */
        _onOverlayClick: function(evt){
            AutocJS.hide();

            evt.stopPropagation();
            evt.preventDefault();

            return AutocJS;
        },
        /**
         *
         * @returns {AutocJS}
         * @private
         */
        _onWindowResize: function(){
            AutocJS.updateLayout();

            return AutocJS;
        }
    };

    // 将 autoc 扩展为一个 jquery 插件
    $.extend( $.fn, {
        autoc: function ( selector, prefix ) {
            var self = this,
                config = {
                    article: self,
                    selector: selector,
                    prefix: prefix
                };

            AutocJS.init( config );
        }
    } );

    window.AutocJS = AutocJS;
    window.autoc = function(config){
       AutocJS.init(config);
    };
    
    return AutocJS;
} ));