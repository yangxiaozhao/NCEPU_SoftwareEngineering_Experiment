DATAS SEGMENT
    ;�˴��������ݶδ���  
DATAS ENDS

STACKS SEGMENT
    ;�˴������ջ�δ���
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
START:
    MOV AX,DATAS
    MOV DS,AX
    MOV CX,10F0H ; ��ʼ��CX
    MOV DL,10H   ; ���ַ���ascll��
DO:              ; ��ӡ����
	MOV AH,02H
	INT 21H
	PUSH DX
	MOV DL,0
	INT 21H
	POP DX
	INC DL       ; ��һ�ַ�
	DEC CH
	JZ NEXT      ; ��CHΪ0��˵���Ѿ���ӡ��һ�У�����лس����в���
	DEC CL
	JMP DO
NEXT:
	MOV CH,10H   ; ˢ��CH��ֵ
	PUSH DX
	MOV AH,02H
	MOV DL,0DH   ; �س�
	INT 21H
	MOV DL,0AH   ; ����
	INT 21H 
	POP DX
	DEC CL
	JNZ DO       ; ��CL��Ϊ0��˵����δ��ӡ��ɣ��������ӡ
	MOV AH,4CH
	INT 21H
CODES ENDS
    END START




