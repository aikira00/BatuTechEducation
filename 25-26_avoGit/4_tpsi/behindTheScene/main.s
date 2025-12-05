	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 15, 0	sdk_version 26, 0
	.globl	_add                            ; -- Begin function add
	.p2align	2
_add:                                   ; @add
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #16
	.cfi_def_cfa_offset 16
	str	w0, [sp, #12]
	str	w1, [sp, #8]
	ldr	w8, [sp, #12]
	ldr	w9, [sp, #8]
	add	w0, w8, w9
	add	sp, sp, #16
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	_main                           ; -- Begin function main
	.p2align	2
_main:                                  ; @main
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #48
	stp	x29, x30, [sp, #32]             ; 16-byte Folded Spill
	add	x29, sp, #32
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	mov	w8, #0                          ; =0x0
	str	w8, [sp, #16]                   ; 4-byte Folded Spill
	stur	wzr, [x29, #-4]
	mov	w8, #10                         ; =0xa
	stur	w8, [x29, #-8]
	mov	w8, #4                          ; =0x4
	stur	w8, [x29, #-12]
	ldur	w0, [x29, #-8]
	ldur	w1, [x29, #-12]
	bl	_add
	mov	x9, sp
                                        ; implicit-def: $x8
	mov	x8, x0
	str	x8, [x9]
	adrp	x0, l_.str@PAGE
	add	x0, x0, l_.str@PAGEOFF
	bl	_printf
	mov	x8, sp
	mov	x9, #34414                      ; =0x866e
	movk	x9, #61467, lsl #16
	movk	x9, #8697, lsl #32
	movk	x9, #16393, lsl #48
	fmov	d0, x9
	str	d0, [x8]
	adrp	x0, l_.str.1@PAGE
	add	x0, x0, l_.str.1@PAGEOFF
	bl	_printf
	ldr	w0, [sp, #16]                   ; 4-byte Folded Reload
	ldp	x29, x30, [sp, #32]             ; 16-byte Folded Reload
	add	sp, sp, #48
	ret
	.cfi_endproc
                                        ; -- End function
	.section	__TEXT,__cstring,cstring_literals
l_.str:                                 ; @.str
	.asciz	"somma: %d"

l_.str.1:                               ; @.str.1
	.asciz	"pigreco: %f"

.subsections_via_symbols
