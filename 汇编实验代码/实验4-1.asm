DATAS SEGMENT
     SENTENCE DB 100		; 句子存放处
     NUMBER1 DB ?		
     ADDRESS1 DB 100 dup(?)
     KEYWORD DB 100		; 关键字存放处
     NUMBER2 DB ?		
     ADDRESS2 DB 100 dup(?) 
     STRING1 DB 'input the keyword$'		; 以下五句为提示语
     STRING2 DB 'input the sentence$'
     STRING3 DB 'match!$'
     STRING4 DB 'no match!$' 
     STRING5 DB 'the location of the keyword is $'       
DATAS ENDS
STACKS SEGMENT
    ;此处输入堆栈段代码
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
NEXT PROC               ; 子程序（回车换行）
	MOV AH,02H
	MOV DL,0AH
	INT 21H
	MOV DL,0DH
	INT 21H 
	RET
NEXT ENDP

RESULT PROC              ; 子程序（编码，将字符个数以十进制形式输出）
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
    XOR BX,BX          ; BX归零
    XOR CX,CX		   ; CX归零
    MOV AX,DATAS
    MOV DS,AX
    MOV AH,09H
    MOV DX,OFFSET STRING1
    INT 21H         ; 提示输入关键字
    CALL NEXT
    MOV DX,OFFSET KEYWORD
    MOV AH,0AH
    INT 21H         ; 输入关键词
    CALL NEXT
    MOV AH,09H
    MOV DX,OFFSET STRING2
    INT 21H			; 提示输入句子
    CALL NEXT  
    MOV DX,OFFSET SENTENCE
    MOV AH,0AH
    INT 21H			 ; 输入句子
    CALL NEXT
    MOV BL,NUMBER1		; 句子长度
    MOV CL,NUMBER2		; 关键字长度
    CMP CL,BL
    JA NOMATCH		; 如果关键字长度大于句子，则一定不匹配
COMPARE:
    LEA DI,ADDRESS1		; DI指向句子首字符
    LEA SI,ADDRESS2		; si指向关键字首字符
    MOV AL,[SI] 		; 关键字首字符放入AL中
    CMP AL,[DI]			; 比较关键字与句子的首字符
    JE TEMPMATCH       ; 若相同，则进入TEMPMATCH
    JNE TEMPNOMATCH		; 若不同，则进入TEMPNOMATCH 
AGAIN:
    PUSH SI
    PUSH DI
    PUSH BX
    LEA BX,ADDRESS2
    SUB SI,BX
    CMP SI,CX 
    JE MATCH          ; 若SI已遍历关键字所有字符，则说明匹配成功
    LEA BX,ADDRESS1
    SUB DI,BX
    POP BX
    CMP DI,BX
    JE NOMATCH			; 若DI已遍历句子所有字符，则说明匹配不成功
    POP DI
    POP SI
    MOV AL,[SI] 
    CMP AL,[DI]
    JE TEMPMATCH
    JNE TEMPNOMATCH
TEMPMATCH:
    ADD SI,1
    ADD DI,1
    JMP AGAIN       ; 关键字和句子均向前推进一个字符
TEMPNOMATCH:
    ADD DI,1
    LEA SI,ADDRESS2
    JMP AGAIN    	; SI重新定位关键字首字符，DI继续向前推进
MATCH:
    LEA DX,STRING3
    MOV AH,09H
    INT 21H
    CALL NEXT
    LEA DX,STRING5
    MOV AH,09H
    INT 21H
    LEA DX,ADDRESS1
    SUB DI,DX
    XOR BX,BX
    MOV BL,NUMBER2
    SUB DI,BX
    INC DI
    MOV AX,DI
    CALL RESULT   
    JMP EXIT     
NOMATCH:
    LEA DX,STRING4
    MOV AH,09H
    INT 21H    
    JMP EXIT       
EXIT:   
    MOV AH,4CH
    INT 21H
CODES ENDS
    END START






















