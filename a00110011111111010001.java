/* Decompiler 4ms, total 412ms, lines 26 */
class a00110011111111010001 {
   static final int a10101011110110100001 = 18;
   static final int a11100101011110010101 = 5;
   static final int a00110011000001111101 = 2078;

   static int a11110001000111110001(int var0, int var1, int var2) {
      int var3 = var1 % 2078 - 18;
      return var3;
   }

   static String a01011010010000011001(int var0, int var1, int[] var2, int var3) {
      StringBuffer var4 = new StringBuffer();
      int[] var5 = var2;
      int var6 = var2.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int var8 = var5[var7];
         if (var8 % a11110001000111110001(-17756, 234834, 2007) == a11110001000111110001(-8803, 220286, -16613)) {
            var4.append((char)(var8 / a11110001000111110001(3116, 1791256, 6804) % 870 - 24));
         }
      }

      return var4.toString();
   }
}
