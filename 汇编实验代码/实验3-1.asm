DATAS SEGMENT
    ;此处输入数据段代码  
DATAS ENDS

STACKS SEGMENT
    ;此处输入堆栈段代码
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
START:
    MOV AX,DATAS
    MOV DS,AX
    MOV CX,10F0H ; 初始化CX
    MOV DL,10H   ; 首字符的ascll码
DO:              ; 打印过程
	MOV AH,02H
	INT 21H
	PUSH DX
	MOV DL,0
	INT 21H
	POP DX
	INC DL       ; 下一字符
	DEC CH
	JZ NEXT      ; 若CH为0，说明已经打印了一行，则进行回车换行操作
	DEC CL
	JMP DO
NEXT:
	MOV CH,10H   ; 刷新CH的值
	PUSH DX
	MOV AH,02H
	MOV DL,0DH   ; 回车
	INT 21H
	MOV DL,0AH   ; 换行
	INT 21H 
	POP DX
	DEC CL
	JNZ DO       ; 若CL不为0，说明还未打印完成，则继续打印
	MOV AH,4CH
	INT 21H
CODES ENDS
    END START




