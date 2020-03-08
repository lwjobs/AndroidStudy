/**
  * 函数功能: 串口接收完成回调函数
  * 输入参数: 无
  * 返 回 值: 无
  * 说    明：无
  */
void HAL_UART_RxCpltCallback(UART_HandleTypeDef *UartHandle)
{
  if(UartHandle==&husart2)
  {
		uartbuff[readcount] = aRxBuffer[1];
			readcount ++;
			if(readcount >=16)
			{
				readcount = 0;
			}
			uint8_t tt = Str_Search((char*)uartbuff,"ON");
			if(tt==1){
				flag = 1;
				memset(uartbuff,0,16);
				strcpy(uartbuff,"");
				readcount = 0;
			}
			uint8_t tt1 = Str_Search((char*)uartbuff,"OFF");
			if(tt1==1){
				flag = 0;
				memset(uartbuff,0,16);
				//strcpy(uartbuff,"");
				readcount = 0;
			}
			
			uint8_t tt2 = Str_Search((char*)uartbuff,"THEME1");
			if(tt2==1){
				flag = 2;
				memset(uartbuff,0,16);
				//strcpy(uartbuff,"");
				readcount = 0;
			}
			
			uint8_t tt3 = Str_Search((char*)uartbuff,"THEME2");
			if(tt3==1){
				flag = 3;
				memset(uartbuff,0,16);
				//strcpy(uartbuff,"");
				readcount = 0;
			}
			
    HAL_UART_Transmit(&husart2,&aRxBuffer[1],1,0);
    HAL_UART_Receive_IT(&husart2,&aRxBuffer[1],1);
  }
}