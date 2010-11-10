package com.alpha.view;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HadithView extends ListActivity
{
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setListAdapter(new HadithListAdapter(this));
  }
	
private class HadithListAdapter extends BaseAdapter {
   public HadithListAdapter(Context context) {
       mContext = context;
   }

   public int getCount() {
       return mDialogue.length;
   }

   public Object getItem(int position) {
       return position;
   }

   public long getItemId(int position) {
       return position;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
       SpeechView sv;
       if (convertView == null) {
           sv = new SpeechView(mContext, "Hadîth "+(position+1)+" - "+mTitles[position],
                   mDialogue[position]);
       } else {
           sv = (SpeechView) convertView;
           sv.setTitle("Hadîth "+(position+1)+" - "+mTitles[position]);
           sv.setDialogue(mDialogue[position]);
       }

       return sv;
   }

   private Context mContext;
   
   private String[] mTitles = 
   {
   		"Les Actes ne valent que par leurs intentions.", 
   		"Al Islam, Al Imane wa Al Ihsane.",
   		"Les Piliers de l'Islam.",
   		"Les dernières oeuvres sont déterminantes.",
   		"La nullité des innovations.",
   		"Du licite et de l'illicite.",
   		"La religion est le bon conseil. ",
   		"Le caractère sacré du musulman.",
   		"La responsabilité dépend de la capacité.",
   		"Ne sont acceptées que les bonnes choses.",
   		"Le scrupule.",
   		"Se consacrer aux choses utiles.",
   		"La foi parfaite.",
   		"L'inviolabilité du sang du musulman.",
   		"Les vertus islamiques.",
   		"Ne laisse pas exploser ta colère !",
   		"La généralité du bel-agir.",
   		"La piété et la haute moralité.",
   		"L'aide d'Allah et Sa protection.",
   		"La pudeur.",
   		"Persévérer dans la voie.",
   		"La facilité de la religion.",
   		"Faire assaut de bonnes actions.",
   		"L'interdiction absolue de l'injustice.",
   		"Le sens large de l'aumône.",
   		"La diversité des bonnes &#339;uvres.",
   		"La piété c'est la haute moralité.",
   		"L'attachement à la Sunna.",
   		"La voie du Paradis.",
   		"Les droits d'Allah.",
   		"La vraie ascèse.",
   		"Tout préjudice est illégal.",
   		"La preuve incombe au demandeur.",
   		"L'empêchement du blâmable est un devoir religieux. ",
   		"La vraie fraternité. ",
   		"L'incitation à &#339;uvrer salutairement.",
   		"La grâce divine.",
   		"L'amour d'Allah.",
   		"Pas de gêne dans la religion.",
   		"Ce Bas-monde au profit de l'Au-delà.",
   		"Le signe de la foi.",
   		"L'immensité de l'indulgence divine."   };
   
   private String[] mDialogue = 
   {
"Le Commandeur des Croyants, Aboû Hafç Omar ben El-Kattâb (que Dieu soit satisfait de lui) a dit: J'ai entendu l' Envoyé de Dieu, salla Allah u alihi wa sallam , (à lui, bénédiction et salut) dire:\n\n"+
"« Les actions ne valent que par leurs intentions ». \n\n Leurs Niyates:\n\n"+
"« Chacun ne recevra la récompense qu'il mérite que selon ce qu'il a entendu faire. A celui qui a accompli l'hégire pour plaire à Allah et à Son Envoyé, son hégire lui sera comptée, comme accomplie en vue de Dieu et de Son Envoyé. Celui qui l'a accomplie pour obtenir quelque bien en ce bas monde, ou pour épouser une femme, son hégire lui sera comptée selon ce qu'il recherchait alors »."
,
"Omar (que Dieu soit satisfait de lui) a dit encore:\n"+
"Un jour, nous étions assis en conférence chez l'Envoyé de Dieu, salla Allah u alihi wa sallam , (à lui bénédiction et salut), et voici que se présenta à nous un homme vêtu d'habits d'une blancheur resplendissante, et aux cheveux très noirs. On ne pouvait distinguer sur lui une trace de voyage, alors que personne d'entre nous ne le connaissait.\n"+
"Il prit alors place, en face du prophète (à lui, bénédiction et salut). Il plaça ses genoux contre les siens, et posa les paumes de ses mains sur les cuisses de celui-ci, et lui dit:\n"+
"«Ô Mohammed, fais-moi connaître l'Islam».\n"+
"L'Envoyé de Dieu, salla Allah u alihi wa sallam , (à lui bénédiction et salut) dit alors:\n"+
"« L'Islâm consiste en ce que tu dois: témoigner qu'il n'est d'autre divinité qu'Allah, et que Mohammed est Son Envoyé, accomplir la prière rituelle, verser la zekâa (impôt rituel) et accomplir le jeûne de Ramadan, ainsi que le pèlerinage à la Maison d'Allah si les conditions de voyage rendent la chose possible ».\n"+
"Son interlocuteur lui répondit: «Tu as dit vrai», et nous de nous étonner, tant de sa question que de son approbation, puis, il reprit «Fais-moi connaître la Foi». Le Prophète répliqua: \n"+
"« La foi consiste en ce que tu dois croire à Allah, à Ses Anges, à Ses Livres. a Son Prophète, au Jugement Dernier. Tu dois croire encore à la prédestination touchant le bien et le mal ». \n"+
"L'homme lui dit encore: «Tu as dit vrai» et il reprit: «Fais-moi connaître la vertu», et le Prophète lui répondit: \n"+
"« La vertu consiste à adorer Dieu, comme si tu Le voyais, car si tu ne Le vois pas, certes, Lui te voit». \n"+
"L'homme lui dit encore: «Fais-moi connaître l'Heure (du Jugement Dernier)», et le Prophéte lui répondit: \n"+
"« Sur l'heure du jugement, l'interrogé n'est pas plus savant que celui qui le questionne ». \n"+
"Là-dessus, l'homme lui dit: «Mais fais m'en connaître les signes précurseurs», et le Prophète lui répondit: \n"+
"« Ce sera lorsque la servante engendrera sa maîtresse, lorsque tu verras les va-nu-pieds, ceux qui vont nus, les miséreux, les pâtres se faire élever des constructions de plus en plus hautes ». \n"+
"Là-dessus, l'homme partit. Je demeurai là longtemps, puis le Prophète dit:\n"+
"« Ô Omar, sais-tu qui m'a interrogé? ».\n"+
"«Non», répondis-je! «Allah et son Envoyé, en cette matière, sont plus savants».\n"+
"« Cet homme-là était l'Archange Gabriel. Il vient de la sorte à vous, pour vous enseigner votre religion ».\n"
,

"Aboû Abd er-Rah'mân, Abd Allah ben Omar, ben el-Khattab (que Dieu soit satisfait d'eux), a dit: J'ai entendu l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) dire:\n"+
"« L'lslam est bâti sur cinq piliers:\n"+
"1° Le témoignage qu'il n'est d'autre Dieu qu'Allah et que Mohamméd est Son Envoyé.\n"+ 
"2° L'accomplissement de la prière rituelle.\n"+ 
"3° L'acquittement de la zekâa (impôt rituel).\n"+ 
"4° Le pèlerinage à la Maison d'Allah\n" +
"5° Le Jeûne du mois de Ramadan »."
,

"Aboû Abd-er-Rah'mân, Abd-Allâh, ben Massoûd (que Dieu soit satisfait de lui) a dit: I'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), le Très véridique, le Prés digne de foi, nous a raconté ci qui suit:\n"+
"« Certes, chacun de vous, lorsqu'il est créé dans le sein de sa mère est d'abord pendant quarante jours une gouttelette, puis devient du sang coagulé pendant une semblable durée de temps, puis enfin durant un même laps de temps, devient comme une bouchée de chair, là-dessus, l'ange lui est envoyé, qui insuffle l'âme, et il est ordonné à celui-ci d'accomplir quatre commandements, à savoir d'inscrire: les moyens de vivre (du nouvel être), le terme de son existence, ses actions, enfin, son infortune, ou son bonheur futur.\n"+
"Par Allah, en dehors de Qui il n'est pas d'autre Divinité, certes, chacun de vous aurait beau œuvrer comme l'ont fait ceux destinés au Paradis, en sorte qu'il s'en approcherait à la distance d'une coudée, alors ce qui a été écrit pour lui prévaudrait, et donc il accomplirait (quand même) les actions des damnés, et il entrerait en Enfer. Et certes, chacun de vous aurait beau oeuvrer comme les damnés, au point de s'approcher de l'Enfer à la distance d'une coudée, alors ce qui a été écrit pour lui prévaudrait, en sorte qu'il accomplirait les actions ties élus et qu'il entrerait (quand même) au Paradis »."
,

"Selon la Mère des Croyants, Oumm Abdallâh Aïcha (que Dieu soit satisfait d'elle), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« Quiconque apporte à notre religion une nouveauté qui n'en provient pas, celui-là est à repousser »."
,   
"Abou Abdallah En-Noumân ben Bachîr (que Dieu soit satisfait de tous deux) a dit: J'ai entendu l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) dire:\n"+
"« Certes, ce qui est permis est évident, et ce qui est défendu ( h'arâm ) est évident aussi. Mais, entre l'un et l'autre, il y a bien des choses équivoques, que la plupart des gens ne savent pas (distinguer). Qui se garde de l'équivoque purifie sa foi et son honneur, mais celui qui y tombe, tombe dans ce qui est défendu: il est semblable au pâtre qui mène ses troupeaux aux alentours d'un territoire gardé, et alors, bien vite il y fera paître. Chaque roi ne possède-t-il pas un territoire gardé? Le champ gardé de Dieu, ce sont les choses défendues.\n"+
"En vérité, il y a dans le corps humain un morceau de chair qui, en bon état, permet au corps tout entier de prospérer et qui, en mauvais état, le corrompt en entier, c'est le coeur »."
,
"Selon Aboû Roqiya Tamîm ben Aoûs ed-Dâri (que Dieu soit satisfait de lui), l'Envoyé de Dieu , alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« La religion, c'est la sincérité ».\n"+
"Quand nous demandâmes: «Envers qui?», il répondit:\n"+
"« Envers Allah, envers Son Livre, envers Son Envoyé, envers les chefs des musulmans, et le commun peuple parmi eux ».\n"
,
"D'après Ibnou Omar(que Dieu soit satisfait de lui et de son père), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« Il m'a été ordonné de combattre les hommes jusqu'à ce qu'ils témoignent qu'il n'est d'autre divinité qu'Allah, et que Mohammed est Son Envoyé, qu'ils accomplissent la prière rituelle, qu'ils acquittent la Zekâa . S'ils exécutent ces choses, ils seront, à mon égard, garantis quant à leurs personnes et à leurs richesses, à moins qu'ils ne transgressent (ouvertement) la loi de l'Islâm, mais Dieu réglera le compte de leurs (intentions vraies) ».\n"
,
"Aboû Horeîra Abd-er-Rah'mân ben Çakhr (que Dieu soit satisfait de lui) a dit: J'ai entendu l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) dire:\n"+
"« Ce que je vous ai défendu de faire évitez-le, et ce que je vous ai ordonné, accomplissez- le dans la mesure où cela vous est possible. Ceux qui vous ont précédé ont péri seulement par l'abon dance de leurs questions et leurs divergences d'opinions à l'égard de leurs Prophètes ».\n"
,
"Selon Aboû Horeîra (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, Bénédiction et salut) a dit:\n"+
"« Certes Allah, Tres Haut est Pureté. Il n'accepte que ce qui est pur. Il ordonne aux croyants ce qu'il a ordonné à Ses Envoyés. Or, Il a dit: ‹ Ô Messagers! mangez de ce qui est permis et agréable et faites du bien .› ( 23:51 ), dit aussi: ‹ Ô les croyants! mangez des (nourritures) licites que Nous vous avons attribuées. › ( 2:172 ).\n"+
"Là-dessus, le Prophète fit allusion à l'homme qui prolonge ses voyages (pieux), qui a des cheveux longs et poudreux et tend les mains vers le ciel, disant: « Ô Seigneur, Ô Seigneur», et cependant il se nourrit de choses défendues, boit des liquides défendus, se revêt d'habits défendus, et il a été nourri (dans son enfance) de choses défendues. «Comment donc pourrait-il être exaucé? ».\n"
,
"Aboû Mohammed el-H'assan ben Ali ben Abi Tâleb descendant de l'Envoyé de Dieu, et son (petit fils (que Dieu soit satisfait de lui et de son père) a dit: J'ai retenu ceci de l'Envoyé de Dieu , alla Allah u alihi WA sallam , (à lui, bénédiction et salut):\n"+
"« Laisse ce quite jette dans le doute (quant à sa licité) pour ce qui ne t'y jette pas ».\n"
,
"Selon Abôu Horeîra (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« Parmi les qualités d'un bon Islâm, il y a le fait pour l'homme de ne pas s'occuper de ce qui ne le regarde pas ».\n"
,
"Selon Aboû H'amza Anas ben Mâlek (que Dieu soit satisfait de lui), serviteur de l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), le Prophète (à lui, bénédiction et salut) a dit:\n"+
"« Aucun de vous ne devient véritablement croyant s'il ne désire pour son frère, ce qu'il désire pour lui-même ».\n"
,
"Selon Abôu Masoûd (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« Il n'est pas licite de faire couler le sang du musulman, sauf s'il s'agit d'un des trois coupables que voici: le fornicateur dont le mariage a été consommé, le meurtrier qui subira le sort de sa victime, et l'apostat qui se sépare de la communauté musulmane ».\n"
,
"Selon Abôu Horeîra (que Dieu soit satisfait de lui), L'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« Que celui qui croit en Allah et au Jugement Dernier parle donc sagement, ou qu'il se taise; que celui qui croit en Allah et au Jugement Dernier, traite donc bien son voisin; que celui qui croit en Allah et au Jugement Dernier, traite donc bien son hôte ».\n"
,
"Selon Aboû Horeîra (que Dieu soit satisfait de lui), un homme dit au Prophète (à lui, bénédiction et salut): «Fais moi une recommandation» (religieuse), celui-ci répondit:\n"+
"« Ne te mets pas en colère ».\n"+
"L'homme revient à la charge plusieurs fois. Il dit (chaque fois):\n"+
"« Ne te mets pas en colère ».\n"
,
"Selon Aboû Yala Chaddâd ben Aoûs (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« Certes, Allah a prescrit de pratiquer le bien en toutes choses. Lors donc que vous tuez, tuez bien. Lors donc que vous égorgez, égorgez bien. Que chacun de vous aiguise son coutelas et traite bien sa victime ».\n"
,
"Selon Aboû D'orr Djoundoub ben Djounâd et Aboû Abd-er-Rah'mân Mou'âd' ben Djabal (que Dieu soit satisfait d'eux), l'Envoyé de Dieu , alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« Crains Dieu en quelque lieu que tu sois; fais suivre le péché de la bonne action qui l'effacera, traite les hommes avec bonté (en faisant preuve) d'un bon naturel ».\n"
,
"Aboû el-Abbâs Abdallâh ben Abbâs (que Dieu soit satisfait de lui et de son père) a dit: «J'étais un jour derrière le Prophète (à lui, bénédiction et salut) (en croupe sur sa mule), et il me dit:\n"+
"« Ô jeune homme, je vais t'enseigner quelques préceptes. Observe les commandements de dieu, il te protègera . Observe les commandements de dieu, tu le trouveras devant toi. Lorsque tu as à demander quelque chose, demande à Allah Lorsque tu as à implorer assistance, implore assistance auprès d'Allah Sache que si la communauté est d'accord, à l'unanimité, pour te faire quelque bien, cela ne te profitera que dans la mesure où Allah te l'aurait assigné, et si elle est d'accord à l'unanimité pour te causer quelque tort, tu n'en pâtiras en rien, sinon dans la mesure où Allah en aurait ainsi décidé à ton encontre. Certes, les calames sont levés et l'encre des feuillets a séché ».\n"+
"Selon une autre version, on a:\n"+
"« observe les commandements d'Allah, tu le trouveras devant toi. Informe-toi de Lui, dans l'aisance: Il te connaître dans la misère. Sache que ce qui destiné à t'éviter, ne t'atteindra pas et ce qui est destiné à t'atteindre, ne te manquera pas. Sache que la constance fait remporter la victoire, la joie suit l'adversité, et la richesse la misère ».\n"
,
"Selon Aboû Masoûd Oqba ben Amr le Compagnon qui prit part à la bataille de Bedr (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« certes, de tout ce que les gens saisissent d'antiques paroles prophétiques, la première est: ‹Si tu n'as pas de pudeur, fais ce que tu veux› ».\n"
,
"Aboû Amr (on dit aussi Aboû Amra), Soufiyân ben Abd Allah (que Dieu soit satisfait de lui), a dit:\n"+
"Je dis à l'Envoyé de Dieu: «Dis-moi une parole touchant l'Islâm, telle que je n'interrogerai à son sujet personne autre que toi». Il dit:\n"+
"« Dis: ‹j'ai foi en Allah›, puis suis la voie droite ».\n"
,
"Selon Aboû Abdallâh Djabir ben Abdallâh, le Compagnon (que Dieu soit satisfait de tous deux), un homme interrogea l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), et lui dit: «A ton avis, si j'accomplis les prières rituelles prescrites; si je jeûne en Ramadhân, si je m'en tiens aux choses permises et évite ce qui est défendu, sans y ajouter aucune autre pratique (suré rogatoire) entrerai-je au Paradis? Le Prophète rèpondit: « Oui ».	 \n"
,
"Selon Abôu Mâlik el-H'ârith ben Açim el Ach'ari (que Dieu soit satisfait de lui,) l'Envoyé de Dieu , alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« La pureté rituelle est la moitié de la religion. Dire: «Louange à Dieu», remplit la balance des bonnes actions. Dire «Gloire à Dieu, Louange à Dieu», remplit l'espace compris entre le ciel et la terre. La prière rituelle est lumière, l'aumône est preuve (de ce que le dû est acquitté), la patience est clarté, le Coran est argument en ta faveur ou à ton détriment (selon que tu en suis ou non les prescriptions). Chaque homme, de grand matin, fait commerce de son âme, la sauvant, ou la faisant périr ».\n"
,
"Selon Aboû D'arr El Ghifârî (qui Dieu soit satisfait de lui), le Prophète (à lui, bénédiction et salut) parmi ce qu'il a rapporté venant de Son Seigneur, (que Sa Puissance et Sa Gloire soient proclamées) déclare qu'il a dit:\n"+
"« Ô Mes Serviteurs, je me suis interdit l'injustice et je vous déclare que je vous l'interdis. Ne soyez donc pas injustes les uns envers les autres. Ô mes Serviteurs, chacun d'entre vous est un égaré, sauf celui que je mène dans le droit chemin: demandez-Moi donc que je vous mène, et je vous y mènerai. Ô mes Serviteurs, chacun d'entre vous est affamé, sauf celui que je nourris, demandez-moi donc de vous nourrir, et je vous nourrirai. Ô mes serviteurs, chacun d'entre vous est nu, sauf celui que J'habille, demandez- Moi donc de vous habiller et je vous habilleri. Ô mes serviteurs, vous pechez de nuit comme de jour et Moi je pardonne tous les péchés, demendez- Moi donc de vous pardonner, et je vous pardonnerai. Ô mes serviteurs, en vain feriez-vous des efforts pour réussir à Me nuire et en vain pour réussir à M'être utiles.\n"+
"Ô mes serviteurs, si du premier au dernier, homme ou génie, vous étiez aussi pieux que l'est celui au cœur le plus pur d'entre vous, cela n'ajouterait rien à Mon Royaume.\n"+
"Ô mes Serviteurs, si du premier au dernier homme ou génie, vous étiez aussi pervers que l'est celui d'entre vous au coeur le plus pervers, cela ne diminuerait en rien mon royaume. Ô mes Serviteurs, si du premier au dernier, homme ou génie, vous vous teniez dans une seule région de la terre pour solliciter mes faveurs et si J'accordais à chacun de vous sa demande, cela n'amoindrirait en rien mes propriétés, pas plus que laiguille n'enlève quoi que ce soit à l'Ocèan en y pénétrant. Ô mes serviteurs, ce sont vos actes seulement dont je tiendrai compte, ensuite. Je vous réumunérerai d'après ceux-ci. Donc celui qui trouve le bonheur, qu'il rende grâce à Dieu et celui qui trouve autre chose, qu'il ne s'en prenne qu'a lui-même ».\n"
,
"WA sallam , (à lui, bénédiction et salut) lui dirent: «Ô Envoyé de Dieu, les gens les plus riches ont accaparé les récompenses, ils prient comme nous, jeûnent comme nous, de plus ils font l'aumône avec le surplus de leurs richesses». Il répondit:\n"+
"« Comment Allah ne vous a pas donné de quoi faire L'aumône? Dire ‹subhana Allah› (‹Gloire à Dieu›), c'est une aumône, ‹Allah u akbar› › (‹Dieu est Grand›), c'est une aumone aussi, et de même: , ‹alhamdu li Allah› › (‹Louanges à Dieu›), ‹la ilaha illa Allah› › (‹Il n'y a d'autre divinité qu'Allah›) chaque fois que vous ordonnez le bien, c'est une aumône, et chaque fois que vous défendez le mal, c'est une aumône chaque fois que vous faites œuvre de chair, vous faites une aumône ».\n"+
"Ils s'écrièrent alors: «Comment, chacun de nous satisferait ses appétits charnels et mériterait par là une rétribution?» Il répondit:\n"+
"« Voyons, celui qui assouvit ses appétits de façons illicite, ne se charge-t-il pas d'un péché? De même celui qui les satisfait de façon licite, obtient une rétribution ».\n"
,
"Selon Aboû Horeïra (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« L'homme a, sur chaque articulation une aumône. Chaque jour où le soleil se lève et où tu réconcilies deux adversaires, tu fais une aumône. En aidant un homme soit à enfourcher sa monture, soit à y placer sa marchandise, tu fais une aumône. Une bonne parole, c'est une aumône, chaque pas que tu fais pour te rendre à la prière rituelle, c'est une aumône, en écartant un obstacle du chemin, tu fais une aumône ».\n"
,
"Selon En-Nawwâs ben Samân(que Dieu soit satisfait de lui), le Prophète (à lui, bénédiction et salut) a dit:\n"+
"« La vertu est (la somme) des bonnes qualités, et le péché, c'est ce qui s'implante dans ton âme, alors qu'il te répugnerait que les gens puissent le savoir ».\n"+
"Wâbiça ben Mabad (que dieu soit satisfait de lui) a dit: j'allai voir l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), et il me dit:\n"+
"« Tu viens me questionner au sujet de la vertu? »\n"+
"«Oui», répondis-je, et il reprit:\n"+
"« Interroge ton coeur La vertu c'est ce par quoi l'âme jouit du repos et le coeur de la tranquillité. Le péché, c'est ce qui s'implante dans l'âme et met le trouble au sein de l'homme, et ceci malgré toutes les consultations religieuses que l'on pourrait te donner (pour te tranquilliser) ».\n"
,
"Aboû Nadjih'El Irbâdh ben Sâriya (que Dieu soit satisfait de lui) a dit: l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) nous fit un jour un prêche qui fit frémir les cœurs et couler les larmes des yeux. Nous lui dimes alors: «Ô Envoyé de Dieu, on dirait un sermon d'adieux. Faites-nous une (dernière) recommandation». Il répondit:\n"+
"« je vous recommande d'adorer Allah (que sa gloire et Sa puissance soient exaltées), d'écouter votre chef, et de lui obéir, votre Emîr fut-il un esclave. Certes, qui de vous vivra, verra de graves discordes. Il vous incombe donc de suivre ma manière d'être et celle des Khalifes réguliers, dirigés (par Dieu). Tenez-vous à cela de toutes vos forces, et gardez-vous des nouveautés religieuses, car toute innovation est égarement ».\n"
,
"Mouâd ben Djabal (que dieu soit satisfait de lui) a dit: je dis: «Ô Envoyé de Dieu, enseigne-moi une action qui me conduise au Paradis, et m'éloigne de l'Enfer». Il répondit:\n"+
"« certes, tu m'as demandé une chose grave, mais elle sera simplifiee pour celui à qui Allah, très Haut, facilite la tâche. Adore Allah, sans lui adjoindre nul associé, observe la prière rituelle, acquitte la zekâa, jeûne durant le mois de Ramadhân, accomplis le pélerinage à la Kaba ».\n"+
"Puis le prophète poursuivit.\n"+
"« Veux - tu que je te montre lesportes du Bien? Le jeûne est un rempart. L'aumône éteint le péché comme l'eau eteint le feu. La prière de l'homme au milieu de la nuit... (est la meilleure) ».\n"+
"puis il récita le verset: « ils s'arrachent de leurs lits... » jusqu'au mot: « .. qu'ils oeuvraient! » ( 32:16-17 ), . puis il reprit:\n"+
"« Veux -tu que je te montre la partie principale de la religion, sa colonne et l'extrémité de son sommet? ».\n"+
"«Oui, ô Envoyé de Dieu», répondis je.Il répondit:\n"+
"« La partie principale de la religion, c'est la soumission à Dieu, sa colonne, c'est la prière, rituelle, et l'extrémité de son sonamet, la guerre sainte ».\n"+
"Il ajouta:\n"+
"« Veux-tu que je t'apprenne ce qui soutient tout cela? ».\n"+
"«Oui, ô Envoyé de Dieu», répondis-je. Il saisit alors sa propre langue et dit:\n"+
"« Garde-toi de celle-là ».\n"+
"je lui dis: «Ô Prophète de Dieu, serons-nous donc susceptibles d'être châtiés pour avoir parlé?». Il me répondit:\n"+
"« Malheureux, est-ce que les gens ne tombent pas en Enfer, face en avant, (ou: sur leur nez) comme conséquence des calomnies que profère leur langue? ».\n"
,
"Selon Aboû Thalaba El-Khouchanî Djourthoûm ben Nâchir (que Dieu soit satisfait de lui), le prophète (à lui, bénédiction et salut), a dit:\n"+
"« certes, Allah, Trés Haut, a fixé des obligations canoniques, ne les négligez pas; il a déterminé des limites, ne les trangressez pas; il a interdit certaines choses, n'en usez pas; il s'est tu à propos d'autres, par miséricorde à votre égard, non par oubli, n'en scrutez donc pas les raisons. »\n"
,
"Aboû Abbâs Sahl ben Sades Sâ'idî (que Dieu soit satisfait de lui), a dit:\n"+
"Un homme se rendit auprès du Prophète (à lui bénédiction et salut), et lui dit: « Ô Envoyé de Dieu, enseigne-moi une action dont l'accomplissement me vaudra l'amour de Dieu et celui des hommes » Il lui répondit:\n"+
"« Méprise les choses d'ici bas, Dieu t'aimera, et méprise ce que possèdent les hommes, et les hommes t'aimeront ».\n"
,
"Selon Aboû Said Sad ben Mâlek ben Sinân, El Khodrî (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« Ne faites pas de mal, et ne rendez pas le mal pour le mal ».\n"
,
"Selon Ibn Abbâs (que Dieu soit satisfait de lui) le Prophète (à lui bénédiction et salut), a dit:\n"+
"« Si l'on accordait aux plaideurs, l'objet de leurs demandes, certes, on en verrait qui réclameraient les richesses et le sang d'autres gens. Mais il appartient au demandeur de faire la preuve et le serment est déféré a celui qui nie ».\n"
,
"Aboû Saîd El Khodrî (que Dieu soit satisfait de lui), a dit qu'il a entendu l'Envoyé de Dieu , alla Allah u alihi WA sallam , (à lui bénédiction et salut), dire:\n"+
"« Si l'un d'entre vous voit ce qui déplait à Dieu, qu'il le combatte de ses mains; si cela ne lui est pas possible, que ce soit par la langue, et si cela encore ne lui est pas possible, que ce soit avec son coeur, c'est là le minimum imposé par la foi ».\n"
,
"Selon Aboû Horëira (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut) a dit:\n"+
"« Ne vous jalousez pas, n'enchérissez pas les uns sur les autres, ne vous häissez pas, et n'aigissez pas avec perversité les uns à l'égard des autres, ne concluez pas d'achats au détriment les uns des autres. Soyez, ô serviteurs de Dieu, tous frères; le musulman est frère du musulman, il ne l'opprime pas, ni ne l'abandonne, et il ne lui ment pas, ni ne lemèprise. La crainte de Dieu est ici », et il dit ceci en montrant trois fois son coeur, puis il ajouta:\n"+
"« Le pire de l'iniquité est de mèpriser son frère musulman. Tout ce qui appartient au musulman est sacré pour le musulman: son sang, son bien, son honneur ».\n"
,
"D'après Aboû Horëira (que Dieu soit satisfait de lui), le Prophète (à lui, bénédiction et salut), a dit:\n"+
"« Quiconque, en ce bas monde, a allégé l'affliction d'un croyant, verra Dieu alléger son affliction au jour du Jugement Dernier. Quiconque secourt un homme dans la gêne, verra Dieu le secourir en ce bas monde et dans l'Autre. Quiconque couvrira les fautes d'un Musulman, verra Dieu les lui couvrir en ce bas monde et dans l'Autre. Dieu aide Son serviteur tant que ce dernier aide son frère. Celui qui parcourt le chemin de la Science (religieuse), Dieu lui aplanira le chemin du Paradis. Tant que les hommes s'assembleront en quelque demeure consacrée à Dieu pour réciter le Coran et pour l'étudier ensemble, la paix du coeur descendra sur eux, la miséricorde les couvrira, les anges les entoureront et Dieu les mentionnera comme étant des Siens. Quant à celui que ses œuvres ont mis en retard. il ne sera pas mis en avance par son lignage ».\n"
,
"Selon Ibn Abbâs (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit parmi ce qu'il a transmis comme venant de Son Seigneur Trés Haut (qu'il soit béni et exalté):\n"+
"« Allah a déterminé les bonnes actions et les péchés». puis il a fait une distinction en cette matière. Lorsque quelqu'un se propose d'accomplir une bonne action, et ne la fait pas, Il la lui inscrit comme si elle était aecomplie, et s'il l'accomplit, Il met à son actif dix bonnes actions, et même sept cents, et encore bien davantage.\n"+
"Mais s'il se propose d'accomplir un péché et ne l'accomplit pas, dieu le lui inscrit comme une bonne action accomplie, et s'il accomplit ce péché. Dieu ne le lui inscrit que comme un seul péché ».\n"
,
"Selon Aboû Horëira (que Dieu soit satisfait de lui) l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui bénédiction et salut), a dit:\n"+
"« Allah Très Haut a dit: ‹A celui qui nuit à un de mes porte-paroles, je déclarerai la guerre. Rien de ce qui M'est agréable ne rapproche autant mon serviteur de Moi, que l'accomplissement des obligations que je lui ai imposées. Mon serviteur ne cessera de se rapprocher de Moi par des pratiques surérogatoires jusqu'a ce que je l'aime, et, lorsque je l'aimerai, je serai l'oreille par laquelle il entendra, le regard par lequel il verra, la main avec laquelle il empoignera, le pied avec lequel il marchera. S'il Me sollicitte, certes, je lui accorderai Ma faveur, s'il implore. Ma protection, certes, je la lui accorderai› ».\n"
,
"Selon Ibn Abbâs (que Dieu soit satisfait de lui), l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut), a dit:\n"+
"« certes, Dieu à cause de moi, pardonnera à ma communauté (les péchés) commis par erreur, oubli, ou contrainte. »\n"
,
"Ibn Omar (que Dieu soit satisfait de lui et de son père), a dit: l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui bénédiction et salut) me prit par l'épaule et dit:\n"+
"« Sois en ce bas monde comme un étranger», ou «comme un passant».\n"+
"Ibn Omar (que Dieu soit satisfait de lui et de son père) disait: «Lorsque tu es au soir, n'attends pas le matin, et lorsque tu es au matin, n'attends las le soir. Prends sur ta santé pour le moment de ta maladie, et sur ta vie pour celui de ta mort ».\n"
,
"Selon Aboû Mohammed Abdallâ ben Amroû ben El-Aç (que Dieu soit satisfait de lui et de son pére), l'Envové de Dieu (à lui, bénédiction et salut), a dit:\n"+
"« aucun de vous ne deviendra véritablement croyant, que lorsque ses passions se plieront aux règles que je vous ai apportées ».\n"+
"Anas (que Dieu soit satisfait de lui), dit qu'il a entendu l'Envoyé de Dieu, alla Allah u alihi WA sallam , (à lui, bénédiction et salut dire).\n"
,
"« Allah très Haut a dit: «Ô fils d'Adam, tant que tu M'invoqueras et mettras ton espoir en Moi, je te pardonnerai les péchés dont tu te seras chargé, sans Me soucier de leur grand nombre. Ô fils d'Adam, si tes péchés atteignent toute l'étendue visible du ciel, et qu'alors tu implores mon pardon, je te pardonnerai. Ô fils d'Adam, si tu viens à Moi, ayant rempli la terre de tes péchés et qu'alors tu Me rencontres cependant que tu n'associes personne d'autre à moi, je te donnerai de quoi la remplir autant de pardon ».\n"
   };

}

private class SpeechView extends LinearLayout {
   public SpeechView(Context context, String title, String words) {
       super(context);

       this.setOrientation(VERTICAL);

       mTitle = new TextView(context);
       mTitle.setText(title);
       mTitle.setTextColor(0xFF00FF00);
       
       addView(mTitle, new LinearLayout.LayoutParams(
               LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

       mDialogue = new TextView(context);
       mDialogue.setText(words);
       addView(mDialogue, new LinearLayout.LayoutParams(
               LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
   }

   public void setTitle(String title) {
       mTitle.setText(title);
   }

   public void setDialogue(String words) {
       mDialogue.setText(words);
   }

   private TextView mTitle;
   private TextView mDialogue;
}
	
}
