var instantiateCodeMirror = function(CmMode, config) {

	var currentmode = "";
	// if no code highlight needed, we apply default settings
	if (!config.edit.codeHighlight) {

		currentmode = 'default';

	// we highlight code according to extension file
	} else {

		if (CmMode === 'txt') {
			currentmode = 'default';
		}
		if (CmMode === 'js') {
			loadJS('./scripts/CodeMirror/mode/javascript/javascript.js');
			currentmode = 'javascript';
		}
		if (CmMode === 'css') {
			loadJS('./scripts/CodeMirror/mode/css/css.js');
			currentmode = 'css';
		}
		if (CmMode === 'html') {
			loadJS('./scripts/CodeMirror/mode/xml/xml.js');
			currentmode = 'text/html';
		}
		if (CmMode === 'xml') {
			loadJS('./scripts/CodeMirror/mode/xml/xml.js');
			currentmode = 'application/xml';
		}
		if (CmMode === 'php') {
			loadJS('./scripts/CodeMirror/mode/htmlmixed/htmlmixed.js');
			loadJS('./scripts/CodeMirror/mode/xml/xml.js');
			loadJS('./scripts/CodeMirror/mode/javascript/javascript.js');
			loadJS('./scripts/CodeMirror/mode/css/css.js');
			loadJS('./scripts/CodeMirror/mode/clike/clike.js');
			loadJS('./scripts/CodeMirror/mode/php/php.js');
			currentmode = 'application/x-httpd-php';
		}
		if (CmMode === 'sql') {
			loadJS('./scripts/CodeMirror/mode/sql/sql.js');
			currentmode = 'text/x-mysql';
		}

	}

	var editor = CodeMirror.fromTextArea(document.getElementById("edit-content"), {
		styleActiveLine : true,
		viewportMargin : Infinity,
		lineNumbers : config.edit.lineNumbers,
		lineWrapping : config.edit.lineWrapping,
		theme : config.edit.theme,
		extraKeys: {
	        "F11": function(cm) {
	          cm.setOption("fullScreen", !cm.getOption("fullScreen"));
	        },
	        "Esc": function(cm) {
	          if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
	        }
	      }
	});

	// we finnaly set option
	editor.setOption("mode", currentmode);
	//console.log('CodeMirror mode  : ' + editor.getOption("mode"));

	return editor;
}
