DATAS SEGMENT
    LETTER DB 0		; ��¼��ĸ����
    DIGHT DB 0		; ��¼���ָ���
    OTHER DB 0		; ��¼�����ַ�����
    STRING0 DB'Please enter a string:$'		; ��ʾ���
    STRING1 DB'letter:$'		
    STRING2 DB'dight:$'		
    STRING3 DB'other:$'		
    STRING DB 80		; ����ַ�����
    NUMBER DB 0
    ADDRESS DB 80 DUP(0)
DATAS ENDS

STACKS SEGMENT
    ;�˴������ջ�δ���
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
    
NEXT PROC            ; �ӳ��򣨻س����У�
	MOV AH,02H
	MOV DL,0AH
	INT 21H
	MOV DL,0DH
	INT 21H 
	RET
NEXT ENDP

RESULT PROC          ; �ӳ��򣨱��룬���ַ�������ʮ������ʽ�����
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
    LEA DX,STRING0      ; ��ʾ�����ַ���
    MOV AH,09H
    INT 21H
    LEA DX,STRING
    MOV AH,0AH
    INT 21H           ; �����ַ���
    AND CX,0
    MOV CL,NUMBER       ; CL��¼�ַ�������
    LEA DI,ADDRESS		; DIָ�����ַ�
AGAIN:
	MOV BL,DS:[DI]
	CMP BL,7AH          ; ���ַ�'z'�Ƚ�
	JA THE_OTHER
	CMP BL,60H			; ���ַ�'`'��'a'ǰһ���ַ����Ƚ�
	JA THE_LETTER
	CMP BL,5AH			; ���ַ�'Z'�Ƚ�
	JA THE_OTHER
	CMP BL,40H			; ���ַ�'@'��'A'ǰһ���ַ����Ƚ�
	JA THE_LETTER
	CMP BL,39H			; ���ַ�'9'�Ƚ�
	JA THE_OTHER
	CMP BL,2FH			; ���ַ�'/'��'0'ǰһ���ַ����Ƚ�
	JA THE_DIGHT
	JMP THE_OTHER
	                                                    
THE_LETTER:			; �ַ�Ϊ��ĸ
	DEC CL
	INC DI
	INC LETTER
	CMP CL,0
	JE EXIT
	JMP AGAIN
THE_DIGHT:			; �ַ�Ϊ����
	DEC CL
	INC DI
	INC DIGHT
	CMP CX,0
	JE EXIT
	JMP AGAIN
THE_OTHER:			; �ַ�Ϊ�����ַ�
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
	CALL RESULT			; �����ĸ����
	CALL NEXT
	LEA DX,STRING2
	MOV AH,09H
	INT 21H
	MOV AL,DIGHT
	CALL RESULT			; ������ָ���
	CALL NEXT
	LEA DX,STRING3
	MOV AH,09H
	INT 21H
	MOV AL,OTHER
	CALL RESULT			; ��������ַ�����
    MOV AH,4CH
    INT 21H
   

CODES ENDS
    END START










