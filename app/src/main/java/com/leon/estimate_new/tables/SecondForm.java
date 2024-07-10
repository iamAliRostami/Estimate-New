package com.leon.estimate_new.tables;

import androidx.room.Ignore;

public record SecondForm(int khakiAb, int khakiFazelab, int asphalutAb, int asphalutFazelab,
                         int sangFarshAb, int sangFarshFazelab, int otherAb, int otherFazelab,
                         String qotreLoole, String jenseLoole, int noeMasraf,
                         String noeMasrafString, int vaziatNasbePomp, int omqeZirzamin,
                         boolean etesalZirzamin, int omqFazelab, boolean chahAbBaran,
                         boolean ezhaNazarA, boolean ezhaNazarF, int qotreLooleI, int jenseLooleI,
                         boolean looleA, boolean looleF, String masrafDescription,
                         String chahDescription, @Ignore String eshterak) {
}
