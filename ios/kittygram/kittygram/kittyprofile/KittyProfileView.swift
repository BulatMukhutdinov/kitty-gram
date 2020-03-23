import SwiftUI
import shared
import KingfisherSwiftUI

struct KittyProfileView: View {
    
    let kitty: Kitty_
    
    private let iconHeight = UIScreen.main.bounds.size.height * 0.4
    
    var body: some View {
        VStack(alignment: .leading) {
            KFImage(URL(string: kitty.icon))
                .resizable()
                .padding()
                .aspectRatio(contentMode: .fill)
                .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: iconHeight)
                
            Text(kitty.breed)
                .font(.system(size: 20, weight: .semibold))
                .padding(.bottom)
            
            Text(kitty.component5())
                .font(.system(size: 16))
            
            Spacer()
        }
        .padding()
    }
}

struct KittyProfile_Previews: PreviewProvider {
    
    static var previews: some View {
        KittyProfileView(kitty: Kitty_(thumb:"https://cdn.royalcanin-weshare-online.io/ZPpjHGsBIYfdNSoCVRpl/v1/bc13bl-hub-abyssinian-adult-standing?w=120&auto=compress&fm=jpg", icon: "https://cdn.royalcanin-weshare-online.io/lGljHGsBG95Xk-RBjPg2/v1/bc13h-hub-abyssinian-adult-black-and-white?w=480&auto=compress&fm=jpg", breed: "Абиссинская", about:"<i>Страна</i>: Эфиопия<br><i>Шерсть</i>: Короткошерстные породы<br><i>Размерная группа</i>: Средние", description: "Абиссинские кошки могут быть очень ласковыми, но только на своих условиях и по настроению. Они очень обаятельны, но могут быть чрезмерно озорными.<br><br>Абиссинская кошка игривая и активная, она хорошо подходит на роль товарища по играм. По мере взросления она крепко привязывается к людям и старается всегда находиться рядом с ними. Абиссинская кошка — общительная, энергичная, преданная и довольно общительная порода.")
        )
    }
}
