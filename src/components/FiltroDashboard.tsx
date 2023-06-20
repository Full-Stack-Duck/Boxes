import ListboxSelector from "./ListboxSelector";

export function FiltroDashboard(){
    return(
      <div className="w-full flex items-center justify-between py-4 border-b-2">
        <p className="font-quicksand font-bold pl-7 text-2xl lg:pl-32">Dashboard</p>
        <div className="lg:pr-32">
          <ListboxSelector />
        </div>
      </div>
        
    )
}