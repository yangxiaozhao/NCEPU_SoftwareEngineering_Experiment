DATAS SEGMENT
    LETTER DB 0		; 记录字母个数
    DIGHT DB 0		; 记录数字个数
    OTHER DB 0		; 记录其他字符个数
    STRING0 DB'Please enter a string:$'		; 提示语句
    STRING1 DB'letter:$'		
    STRING2 DB'dight:$'		
    STRING3 DB'other:$'		
    STRING DB 80		; 存放字符串处
    NUMBER DB 0
    ADDRESS DB 80 DUP(0)
DATAS ENDS

STACKS SEGMENT
    ;此处输入堆栈段代码
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
    
NEXT PROC            ; 子程序（回车换行）
	MOV AH,02H
	MOV DL,0AH
	INT 21H
	MOV DL,0DH
	INT 21H 
	RET
NEXT ENDP

RESULT PROC          ; 子程序（编码，将字符个数以十进制形式输出）
	CBW
	MOV BL,10D
	DIV BL
	CMP AL,0
	JE DO
	MOV DL,AL
	ADD DL,30H
	PUSH AX
	MOV AH,02H
	INT 21H 
	POP AX
DO:
	MOV DL,AH
	ADD DL,30H
	MOV AH,02H
	INT 21H
	RET
RESULT ENDP

START:
    MOV AX,DATAS
    MOV DS,AX
    LEA DX,STRING0      ; 提示输入字符串
    MOV AH,09H
    INT 21H
    LEA DX,STRING
    MOV AH,0AH
    INT 21H           ; 输入字符串
    AND CX,0
    MOV CL,NUMBER       ; CL记录字符串长度
    LEA DI,ADDRESS		; DI指向首字符
AGAIN:
	MOV BL,DS:[DI]
	CMP BL,7AH          ; 与字符'z'比较
	JA THE_OTHER
	CMP BL,60H			; 与字符'`'（'a'前一个字符）比较
	JA THE_LETTER
	CMP BL,5AH			; 与字符'Z'比较
	JA THE_OTHER
	CMP BL,40H			; 与字符'@'（'A'前一个字符）比较
	JA THE_LETTER
	CMP BL,39H			; 与字符'9'比较
	JA THE_OTHER
	CMP BL,2FH			; 与字符'/'（'0'前一个字符）比较
	JA THE_DIGHT
	JMP THE_OTHER
	                                                    
THE_LETTER:			; 字符为字母
	DEC CL
	INC DI
	INC LETTER
	CMP CL,0
	JE EXIT
	JMP AGAIN
THE_DIGHT:			; 字符为数字
	DEC CL
	INC DI
	INC DIGHT
	CMP CX,0
	JE EXIT
	JMP AGAIN
THE_OTHER:			; 字符为其他字符
	DEC CL
	INC DI
	INC OTHER
	CMP CX,0
	JE EXIT
	JMP AGAIN
EXIT:
	CALL NEXT 
	LEA DX,STRING1     
	MOV AH,09H
	INT 21H
	MOV AL,LETTER
	CALL RESULT			; 输出字母个数
	CALL NEXT
	LEA DX,STRING2
	MOV AH,09H
	INT 21H
	MOV AL,DIGHT
	CALL RESULT			; 输出数字个数
	CALL NEXT
	LEA DX,STRING3
	MOV AH,09H
	INT 21H
	MOV AL,OTHER
	CALL RESULT			; 输出其他字符个数
    MOV AH,4CH
    INT 21H
   

CODES ENDS
    END START










