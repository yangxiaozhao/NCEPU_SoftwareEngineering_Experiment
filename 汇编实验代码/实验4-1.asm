DATAS SEGMENT
     SENTENCE DB 100		; ���Ӵ�Ŵ�
     NUMBER1 DB ?		
     ADDRESS1 DB 100 dup(?)
     KEYWORD DB 100		; �ؼ��ִ�Ŵ�
     NUMBER2 DB ?		
     ADDRESS2 DB 100 dup(?) 
     STRING1 DB 'input the keyword$'		; �������Ϊ��ʾ��
     STRING2 DB 'input the sentence$'
     STRING3 DB 'match!$'
     STRING4 DB 'no match!$' 
     STRING5 DB 'the location of the keyword is $'       
DATAS ENDS
STACKS SEGMENT
    ;�˴������ջ�δ���
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
NEXT PROC               ; �ӳ��򣨻س����У�
	MOV AH,02H
	MOV DL,0AH
	INT 21H
	MOV DL,0DH
	INT 21H 
	RET
NEXT ENDP

RESULT PROC              ; �ӳ��򣨱��룬���ַ�������ʮ������ʽ�����
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
    XOR BX,BX          ; BX����
    XOR CX,CX		   ; CX����
    MOV AX,DATAS
    MOV DS,AX
    MOV AH,09H
    MOV DX,OFFSET STRING1
    INT 21H         ; ��ʾ����ؼ���
    CALL NEXT
    MOV DX,OFFSET KEYWORD
    MOV AH,0AH
    INT 21H         ; ����ؼ���
    CALL NEXT
    MOV AH,09H
    MOV DX,OFFSET STRING2
    INT 21H			; ��ʾ�������
    CALL NEXT  
    MOV DX,OFFSET SENTENCE
    MOV AH,0AH
    INT 21H			 ; �������
    CALL NEXT
    MOV BL,NUMBER1		; ���ӳ���
    MOV CL,NUMBER2		; �ؼ��ֳ���
    CMP CL,BL
    JA NOMATCH		; ����ؼ��ֳ��ȴ��ھ��ӣ���һ����ƥ��
COMPARE:
    LEA DI,ADDRESS1		; DIָ��������ַ�
    LEA SI,ADDRESS2		; siָ��ؼ������ַ�
    MOV AL,[SI] 		; �ؼ������ַ�����AL��
    CMP AL,[DI]			; �ȽϹؼ�������ӵ����ַ�
    JE TEMPMATCH       ; ����ͬ�������TEMPMATCH
    JNE TEMPNOMATCH		; ����ͬ�������TEMPNOMATCH 
AGAIN:
    PUSH SI
    PUSH DI
    PUSH BX
    LEA BX,ADDRESS2
    SUB SI,BX
    CMP SI,CX 
    JE MATCH          ; ��SI�ѱ����ؼ��������ַ�����˵��ƥ��ɹ�
    LEA BX,ADDRESS1
    SUB DI,BX
    POP BX
    CMP DI,BX
    JE NOMATCH			; ��DI�ѱ������������ַ�����˵��ƥ�䲻�ɹ�
    POP DI
    POP SI
    MOV AL,[SI] 
    CMP AL,[DI]
    JE TEMPMATCH
    JNE TEMPNOMATCH
TEMPMATCH:
    ADD SI,1
    ADD DI,1
    JMP AGAIN       ; �ؼ��ֺ;��Ӿ���ǰ�ƽ�һ���ַ�
TEMPNOMATCH:
    ADD DI,1
    LEA SI,ADDRESS2
    JMP AGAIN    	; SI���¶�λ�ؼ������ַ���DI������ǰ�ƽ�
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






















