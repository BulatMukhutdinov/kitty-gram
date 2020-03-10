import SwiftUI
import shared
import KingfisherSwiftUI

struct KittyProfileView: View {
    
    let kitty: Kitty
    
    private let iconHeight = UIScreen.main.bounds.size.height * 0.4
    
    var body: some View {
        VStack(alignment: .leading) {
            KFImage(URL(string: kitty.icon))
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: iconHeight)
            
            Text(kitty.breed)
                .font(.system(size: 20, weight: .semibold))
                .padding(.bottom)
            
            Text(kitty.component3())
                .font(.system(size: 16))
            
            Spacer()
        }
        .padding()
    }
}

struct KittyProfile_Previews: PreviewProvider {
    
    static var previews: some View {
        KittyProfileView(kitty: Kitty(icon: "https://www.royal-canin.ru/upload/iblock/a9c/glavnaya.jpg", breed: "Абиссинская", description: "Как правило, по своему характеру абиссинские кошки весьма активны, они стремятся принимать участие во всех домашних делах. Могут быть очень ласковы, но только на своих условиях и по настроению. Необыкновенно обаятельны, но иногда бывают очень озорными. Эти игривые, подвижные создания хорошо уживутся с любым другим домашним животным, которое сможет стать им товарищем по играм. Они сильно привязываются к людям и стараются всегда находиться рядом с ними. Все эти особенности характера и поведения абиссинской кошки не дадут ее хозяину заскучать или почувствовать себя одиноким."))
    }
}
